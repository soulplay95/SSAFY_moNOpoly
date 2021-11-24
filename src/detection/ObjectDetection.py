## Chair Detection with Mask R-CNN
import torch
import PIL.Image as pilimg
import numpy as np
import matplotlib
matplotlib.use('TkAgg')
import matplotlib.pyplot as plt
import torchvision
from torchvision import transforms
from torchvision.utils import draw_bounding_boxes
from torchvision.io import read_image
from sortChairs import sortChairs

classes = {
0: 'unlabeled',
1: 'person',
2: 'bicycle',
3: 'car',
4: 'motorcycle',
5: 'airplane',
6: 'bus',
7: 'train',
8: 'truck',
9: 'boat',
10: 'traffic light',
11: 'fire hydrant',
12: 'street sign',
13: 'stop sign',
14: 'parking meter',
15: 'bench',
16: 'bird',
17: 'cat',
18: 'dog',
19: 'horse',
20: 'sheep',
21: 'cow',
22: 'elephant',
23: 'bear',
24: 'zebra',
25: 'giraffe',
26: 'hat',
27: 'backpack',
28: 'umbrella',
29: 'shoe',
30: 'eye glasses',
31: 'handbag',
32: 'tie',
33: 'suitcase',
34: 'frisbee',
35: 'skis',
36: 'snowboard',
37: 'sports ball',
38: 'kite',
39: 'baseball bat',
40: 'baseball glove',
41: 'skateboard',
42: 'surfboard',
43: 'tennis racket',
44: 'bottle',
45: 'plate',
46: 'wine glass',
47: 'cup',
48: 'fork',
49: 'knife',
50: 'spoon',
51: 'bowl',
52: 'banana',
53: 'apple',
54: 'sandwich',
55: 'orange',
56: 'broccoli',
57: 'carrot',
58: 'hot dog',
59: 'pizza',
60: 'donut',
61: 'cake',
62: 'chair',
63: 'couch',
64: 'potted plant',
65: 'bed',
66: 'mirror',
67: 'dining table',
68: 'window',
69: 'desk',
70: 'toilet',
71: 'door',
72: 'tv',
73: 'laptop',
74: 'mouse',
75: 'remote',
76: 'keyboard',
77: 'cell phone',
78: 'microwave',
79: 'oven',
80: 'toaster',
81: 'sink',
82: 'refrigerator',
83: 'blender',
84: 'book',
85: 'clock',
86: 'vase',
87: 'scissors',
88: 'teddy bear',
89: 'hair drier',
90: 'toothbrush',
91: 'hair brush'
}

# image path
num = 1
# labels_number = int(input())
def objectDetection(image_name, labels_number, score_threshold):
    inputImgPath = image_name
    imageName = image_name.split('\\')[-1].split('.')[0]
    outputImgPath = "C:/Program Files (x86)/ipTIME/ipTIME_CAM_Utility/Visualize/" + imageName + f'_result_{classes[labels_number]}_{score_threshold}.png'

    # transforms
    Trans2Pil = transforms.ToPILImage()
    Trans2Tensor = transforms.ToTensor()

    # get image
    image = pilimg.open(inputImgPath)
    imgTensor = Trans2Tensor(image)

    # practice detection
    model = torchvision.models.detection.fasterrcnn_mobilenet_v3_large_fpn(pretrained=True)
    model.eval()
    predictions = model(imgTensor.unsqueeze_(0))[0]

    # draw detection box
    # chair == 62 / human == 1
    boxes = predictions['boxes'][(predictions['labels'] == labels_number) & (predictions['scores'] > score_threshold)]
    labels = predictions['labels'][(predictions['labels'] == labels_number) & (predictions['scores'] > score_threshold)]
    scores = predictions['scores'][(predictions['labels'] == labels_number) & (predictions['scores'] > score_threshold)]
    # # masks = predictions['masks'][(predictions['labels'] == labels_number) & (predictions['scores'] > score_threshold)].squeeze()

	
    coordinates = []
    for box in boxes:
    	x1 = round(box[0].item())
    	y1 = round(box[1].item())
    	x2 = round(box[2].item())
    	y2 = round(box[3].item())
    	coordinates.append([x1, y1, x2, y2])
    	

    # draw output image of human detection
    if labels_number == 1:
        imgInput = read_image(inputImgPath)
        labels = labels.tolist()
        scores = scores.tolist()
        for i, (label, score) in enumerate(zip(labels, scores)):
            labels[i] = classes[label] + " #" + str(i) + " {:.4f}".format(scores[i])
        color = [(255, 0, 0) for i in range(len(coordinates))]
        imgOutput = draw_bounding_boxes(imgInput, boxes=boxes, labels=labels, colors=color, width=2)

        # save image
        try:
            imageOut = Trans2Pil(imgOutput)
            imageOut.save(outputImgPath)
        except OSError:
            print("err")
            pass

    if labels_number == 62:
        coordinates = sortChairs(coordinates)
        #visualizeChair(image_name.split('/')[1], coordinates)
    
    return coordinates



