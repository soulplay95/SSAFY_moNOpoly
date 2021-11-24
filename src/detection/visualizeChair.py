import torch
from torchvision.utils import draw_bounding_boxes
from torchvision.io import read_image
from torchvision import transforms
from IoU import IoU
import numpy as np


def visualizeChair(img_name, chairCoordinates):
    Trans2Pil = transforms.ToPILImage()
    outputPath = "C:/Program Files (x86)/ipTIME/ipTIME_CAM_Utility/Visualize/"
    imageName = img_name.split('/')[-1].split('.')[0]
    #imageName = img_name.split('/')[-1].split('.')[0]

    img = read_image(img_name)
    
    for i, chair in enumerate(chairCoordinates):
        box = torch.FloatTensor([chair])
        label = ["chair #" + str(i)]
        img = draw_bounding_boxes(img, boxes=box, labels=label, colors=[(255, 255, 0)], width=2)
    
    img = Trans2Pil(img)
    img.save(outputPath + "chair.png")