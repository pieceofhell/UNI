import cv2
import numpy as np
import pyautogui
import time

# Function to locate a template image within a target image at multiple scales
def find_template(template_path, target_path):
    # Read the template and target images
    template = cv2.imread(template_path, 0)
    target = cv2.imread(target_path, 0)

    # Initialize a list to store the match locations
    match_locations = []

    # Define a range of scales to consider
    scales = np.linspace(0.2, 1.0, 5)  # Adjust the range as needed

    for scale in scales:
        # Resize the template to the current scale
        resized_template = cv2.resize(template, None, fx=scale, fy=scale)

        # Perform template matching
        res = cv2.matchTemplate(target, resized_template, cv2.TM_CCOEFF_NORMED)
        threshold = 0.8  # You can adjust this threshold based on your needs
        loc = np.where(res >= threshold)

        # Add the match locations at the current scale to the list
        for pt in zip(*loc[::-1]):
            match_locations.append((pt[0], pt[1], int(resized_template.shape[1]), int(resized_template.shape[0])))

    return match_locations

# Specify the path to the template image you want to locate
template_path = "tirarFoto.png"

while True:
    # Take a screenshot of the main monitor
    screenshot = pyautogui.screenshot()

    # Convert the screenshot to grayscale
    screenshot = cv2.cvtColor(np.array(screenshot), cv2.COLOR_RGB2GRAY)

    # Save the screenshot to a file
    screenshot_path = "screenshot.png"
    cv2.imwrite(screenshot_path, screenshot)

    # Find the template in the screenshot at multiple scales
    match_locations = find_template(template_path, screenshot_path)

    # Check if any matches are found
    if match_locations:
        print("Template found on the screen!")
    else:
        print("Template not found on the screen!")

    # Wait for a few seconds before taking the next screenshot
    time.sleep(1)  # Adjust the interval as needed
