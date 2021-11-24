import torch
from torchvision.utils import draw_bounding_boxes
from torchvision.io import read_image
from torchvision import transforms
from IoU import IoU
import numpy as np


def visualize(img_name, chairCoordinates, humanCoordinates):
    Trans2Pil = transforms.ToPILImage()
    outputPath = "C:/Program Files (x86)/ipTIME/ipTIME_CAM_Utility/Visualize/"
    imageName = img_name.split('\\')[-1].split('.')[0]

    img = read_image(img_name)

    IoUres = []
    thresh = 0.1
    for i, chair in enumerate(chairCoordinates):
        tmpIou = 0
        for j, human in enumerate(humanCoordinates[:]):
            tmpIou = round(IoU(chair, human), 2)
            if tmpIou > thresh:
                IoUres.append([tmpIou, i, j])
    IoUres.sort(reverse=True)
    #print(IoUres)
    
    chair_visited = []
    human_visited = []
    IoUres2 = []
    for k, IoU_i_j in enumerate(IoUres):
        if len(IoUres2) >= len(humanCoordinates): break
        if (IoU_i_j[1] not in chair_visited) and (IoU_i_j[2] not in human_visited):
            IoUres2.append(IoU_i_j)
            chair_visited.append(IoU_i_j[1])
            human_visited.append(IoU_i_j[2])
    #print(IoUres2, chair_visited, human_visited)
    
    result = [(i, False) for i in range(len(chairCoordinates))]
    for k, IoU_i_j in enumerate(IoUres2):
        iou = IoU_i_j[0]
        i = IoU_i_j[1]
        j = IoU_i_j[2]
        box = torch.FloatTensor([chairCoordinates[i], humanCoordinates[j]])
        label = ["chair #" + str(i) + " IoU= " + str(iou), "person #" + str(j) +" is sitting on chair #" + str(i) ]
        color = [(255, 255, 0), (255, 0, 0)]
        img = draw_bounding_boxes(img, boxes=box, labels=label, colors=color, width=2)
        result[i] = (i, True)
    
    img = Trans2Pil(img)
    img.save(outputPath + imageName + "_IoU_" + str(thresh) + ".png")
    return result
    
 
