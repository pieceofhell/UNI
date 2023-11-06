from PIL import Image
import pytesseract
import pyautogui
import time
import re

start_time = time.time()

# Define the coordinates of the top-left and bottom-right corners of the region to capture
x1, y1 = 740, 400  # Top-left corner
x2, y2 = 1180, 900  # Bottom-right corner

while True:
    # Capture the screenshot of the specified region
    screenshot = pyautogui.screenshot(region=(x1, y1, x2 - x1, y2 - y1))

    # Save the screenshot to a file (optional)
    screenshot.save("screenshot.png")

    # Specify the path to the template image you want to locate (optional)
    currentImage = "screenshot.png"

    try:
        img = Image.open(currentImage)
        rawText = pytesseract.image_to_string(img)
        processedText = "".join([char.lower() for char in rawText if char != " "])

        cleanText = re.sub(r"[^a-zA-Z0-9]", "", processedText)
        substring0 = "biometria"
        substring1 = "tirarfoto"
        substring2 = "tirar"
        substring3 = "foto"
        substring4 = "tiraroutra"
        substring5 = "enviar"
        substring6 = "outra"

        if (
            substring0 in cleanText
            or substring1 in cleanText
            or substring2 in cleanText
            or substring3 in cleanText
            or substring4 in cleanText
            or substring5 in cleanText
            or substring6 in cleanText
        ):
            print("SUBSTRING FOUND!")

            # Perform a mouse click at the calculated coordinates
            time.sleep(2)
            pyautogui.click(880, 740)
            print("BUTTON CLICKED!")

            # Start the search for the second button click
            time.sleep(2)

            isPictureTaken = True
            pyautogui.click(955, 780)

            if (
                substring4 in cleanText
                or substring5 in cleanText
                or substring6 in cleanText
            ):
                print("SECOND SUBSTRING FOUND!")
                time.sleep(2)
                # pyautogui.click(955, 780)
                print("CLICK!")
                time.sleep(2)
                print("PROCESS COMPLETE!")
                time.sleep(2)
                # Upon taking the picture, perform a mouse
                # click at the calculated coordinates
            else:
                print("SUBSTRING 2 NOT FOUND.")
        else:
            print("SUBSTRING NOT FOUND.")
            isPictureTaken = False

    # the following piece of code treats the KeyboardInterrupt exception and prints that the program has been succesfully stopped

    except KeyboardInterrupt as e:
        print("--- %s seconds ---" % (time.time() - start_time))

    time.sleep(5)  # Adjust the interval as needed
