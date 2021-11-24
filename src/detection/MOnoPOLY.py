from PIL.Image import NONE
from ObjectDetection import objectDetection
from Watcher import Target
import requests
from visualizeChair import visualizeChair


if __name__ == "__main__":
    # Chair Detection
    chairCoordinates = objectDetection("./img1.jpg", 62, 0.45)
    visualizeChair("./img1.jpg", chairCoordinates)
    print('chair ', chairCoordinates)
    
    for i, chairCoordinate in enumerate(chairCoordinates):
        seats_info = {
                        "coordinateX": (chairCoordinate[0] + chairCoordinate[2]) // 2,
                        "coordinateY": (chairCoordinate[1] + chairCoordinate[3]) // 2,
                        "id": i,
                        "sectionId": 9999,
                    }
        r = requests.post('https://i5a209.p.ssafy.io:12346/seat', json = seats_info).json()
        print(r)
    
    w = Target()
    w.run()