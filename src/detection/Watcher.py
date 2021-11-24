from os.path import exists
import os
import time
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
from ObjectDetection import objectDetection
from Occupancy2 import 자리확인
from visualize import visualize
from sortChairs import sortChairs
import requests

class Target:
    watchDir = os.path.dirname(os.path.realpath(__file__)) #Watcher.py 가 있는 폴더를 감시

    def __init__(self):
        self.observer = Observer()   #observer객체를 만듦

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
#FileSystemEventHandler 클래스를 상속받음.
#아래 핸들러들을 오버라이드 함

    def on_created(self, event): # 파일, 디렉터리가 생성되면 실행
        print(f'{event.src_path}이 생성됨')
        chairCoordinates = objectDetection("./img1.jpg", 62, 0.45)
        print('chair ', chairCoordinates)
        humanCoordinates = objectDetection(event.src_path, 1, 0.7)
        print('human ', humanCoordinates)
        
        for j, human in enumerate(humanCoordinates):
            print(human)
            human[1], human[3] = (human[1] + human[3]) // 2, (human[1] + human[3]) // 3 * 2
            print(human)

        data = visualize(event.src_path, chairCoordinates, humanCoordinates)
        print(data)
        print('Occ2 실행!')
        자리확인(data)
        #return event.src_path # 파일명 리턴