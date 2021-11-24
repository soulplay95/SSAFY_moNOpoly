from ObjectDetection import objectDetection
from Occupancy import Occupancy
from visualize import visualize
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
import requests
import os
import time


class Target:
    watchDir = os.path.dirname(os.path.realpath(__file__))  # Watcher.py 가 있는 폴더를 감시

    def __init__(self):
        self.observer = Observer()  # observer객체를 만듦

    def run(self):
        event_handler = Handler()
        self.observer.schedule(event_handler, self.watchDir, recursive=True)
        self.observer.start()
        try:
            while True:
                time.sleep(1)
        except:
            self.observer.stop()
            print("Error")
            self.observer.join()


class Handler(FileSystemEventHandler):
    # FileSystemEventHandler 클래스를 상속받음.
    # 아래 핸들러들을 오버라이드 함

    def on_created(self, event):  # 파일, 디렉터리가 생성되면 실행
        print(f'{event.src_path} 이 생성됨')
        global file_name
        file_name = f'{event.src_path}'  # 파일명 리턴
        time.sleep(1)
        # Chair Detection
        chairCoordinates = objectDetection(file_name, 62, 0.87)
        print('chair ', chairCoordinates)

        for i, chairCoordinate in enumerate(chairCoordinates):
            seats_info = {'id': i, 'coordinateX': (
                chairCoordinate[0] + chairCoordinate[2]) // 2, 'coordinateX': (chairCoordinate[1] + chairCoordinate[3]) // 2}
        r = requests.post('https://i5a209.p.ssafy.io:12346/seat', json=seats_info).json()

        print("test")
        # File Made

        humanCoordinates = objectDetection(file_name, 1, 0.7)
        print('human ', humanCoordinates)

        data = visualize(file_name, chairCoordinates, humanCoordinates)
        print('arr 실행!')
        Occupancy(data)
    


if __name__ == "__main__":
    w = Target()
    w.run()