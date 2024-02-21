#include <iostream>
#include <Windows.h>

int main() {
    while (true) {
        if (GetAsyncKeyState(VK_CONTROL) & 0x8000 &&
            GetAsyncKeyState(VK_MENU) & 0x8000 &&
            GetAsyncKeyState('F') & 0x8000) {
            // Open Chrome or perform desired action
            system("start chrome");
        }

        // Add a delay or other logic to avoid high CPU usage
        Sleep(100);
    }

    return 0;
}
