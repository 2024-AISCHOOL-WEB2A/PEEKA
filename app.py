from flask import Flask, render_template, request, jsonify, Response, url_for
from flask_cors import CORS
import cv2
import dlib
import datetime
import os
import numpy as np

app = Flask(__name__)
CORS(app)  # CORS 지원 추가

# dlib의 얼굴 감지기 초기화
detector = dlib.get_frontal_face_detector()

def detect_and_mark_face(image):
    rgb_image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    dets, scores, _ = detector.run(rgb_image, 1, 0)
    
    if len(dets) > 0:
        # 가장 큰 얼굴 선택 (여러 얼굴이 감지된 경우)
        face = max(dets, key=lambda d: (d.right() - d.left()) * (d.bottom() - d.top()))
        
        x, y = face.left(), face.top()
        w, h = face.right() - face.left(), face.bottom() - face.top()

        # 얼굴 주변으로 약간의 여유 공간 추가
        padding = int(w * 0.1)  # 10% 패딩
        x = max(0, x - padding)
        y = max(0, y - padding)
        w = min(image.shape[1] - x, w + 2 * padding)
        h = min(image.shape[0] - y, h + 2 * padding)
        
        # 얼굴에 사각형 그리기
        cv2.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 2)
        
        # 얼굴 부분 크롭
        face_image = image[y:y+h, x:x+w]
        return image, face_image, True
    
    return image, None, False

def gen_frames():
    camera = cv2.VideoCapture(0)
    while True:
        success, frame = camera.read()
        if not success:
            break
        else:
            marked_frame, _, face_detected = detect_and_mark_face(frame)
            if face_detected:
                cv2.putText(marked_frame, "Face Detected", (10, 50), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2)
            ret, buffer = cv2.imencode('.jpg', marked_frame)
            frame = buffer.tobytes()
            yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/face_recognition')
def face_recognition():
    return render_template('face_recognition.html')

@app.route('/video_feed')
def video_feed():
    return Response(gen_frames(), mimetype='multipart/x-mixed-replace; boundary=frame')

@app.route('/capture', methods=["POST"])
def capture():
    if 'image' not in request.files:
        return jsonify({
            "success": False, 
            "message": "No file part", 
            "redirect": "http://localhost:8085/ProjectPeeka/authentication_failure.jsp"
        })
    
    file = request.files['image']
    
    if file.filename == '':
        return jsonify({
            "success": False, 
            "message": "No selected file", 
            "redirect": "http://localhost:8085/ProjectPeeka/authentication_failure.jsp"
        })
    
    nparr = np.frombuffer(file.read(), np.uint8)
    image = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    
    marked_image, face_image, face_detected = detect_and_mark_face(image)
    
    if face_detected:
        now = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
        filename_full = f"full_{now}.jpg"
        filename_face = f"face_{now}.jpg"
        filepath_full = os.path.join('static/upload', filename_full)
        filepath_face = os.path.join('static/upload', filename_face)
        
        cv2.imwrite(filepath_full, marked_image)
        cv2.imwrite(filepath_face, face_image)
        
        return jsonify({
            "success": True,
            "message": "얼굴이 인식되었습니다.",
            "redirect": "http://localhost:8085/ProjectPeeka/authentication_success.jsp"
        })
    else:
        return jsonify({
            "success": False,
            "message": "얼굴이 감지되지 않았습니다.",
            "redirect": "http://localhost:8085/ProjectPeeka/authentication_failure.jsp"
        })


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)