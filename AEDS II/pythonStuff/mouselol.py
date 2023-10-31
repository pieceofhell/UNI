import pyautogui
import time

print("Move your mouse cursor to the top-left corner of the region you want to screenshot.")
time.sleep(2)
top_left = pyautogui.position()
print("Top-left corner coordinates:", top_left)

print("Move your mouse cursor to the bottom-right corner of the region you want to screenshot.")
time.sleep(2)
bottom_right = pyautogui.position()
print("Bottom-right corner coordinates:", bottom_right)
