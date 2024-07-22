from flask import Flask, render_template, request, jsonify
import cv2
import dlib
import datetime
import os
import numpy as np

app = Flask(__name__)

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

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/capture', methods=["POST"])
def capture():
    if 'image' not in request.files:
        return jsonify({"error": "No file part"})
    
    file = request.files['image']
    
    if file.filename == '':
        return jsonify({"error": "No selected file"})
    
    # 이미지를 NumPy 배열로 변환
    nparr = np.frombuffer(file.read(), np.uint8)
    image = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    
    # 얼굴 감지 및 마킹
    marked_image, face_image, face_detected = detect_and_mark_face(image)
    
    if face_detected:
        # 얼굴이 감지되었을 때 파일 저장
        now = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
        filename_full = f"full_{now}.jpg"
        filename_face = f"face_{now}.jpg"
        filepath_full = os.path.join('static/upload', filename_full)
        filepath_face = os.path.join('static/upload', filename_face)
        
        cv2.imwrite(filepath_full, marked_image)  # 사각형이 그려진 전체 이미지 저장
        cv2.imwrite(filepath_face, face_image)    # 크롭된 얼굴 이미지 저장
        
        result = {
            "message": "얼굴이 감지되었습니다.",
            "filename_full": filename_full,
            "filename_face": filename_face
        }
    else:
        result = {"message": "얼굴이 감지되지 않았습니다."}
    
    return jsonify(result)

if __name__ == '__main__':
    # HTTPS로 Flask 애플리케이션 실행
    app.run(host='0.0.0.0', port=5000, ssl_context='adhoc', debug=True)