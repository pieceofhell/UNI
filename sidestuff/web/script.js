// script.js

document.addEventListener("scroll", function () {
  clearTimeout(window.snapping);

  window.snapping = setTimeout(() => {
    const sections = document.querySelectorAll(".section");
    let closestSection = null;
    let minDistance = Infinity;

    sections.forEach((section) => {
      const rect = section.getBoundingClientRect();
      const distance = Math.abs(rect.top);
      if (distance < minDistance) {
        minDistance = distance;
        closestSection = section;
      }
    });

    closestSection.scrollIntoView({
      behavior: "smooth",
      block: "start",
    });
  }, 150);
});

document.querySelectorAll("nav ul li a").forEach((anchor) => {
  anchor.addEventListener("click", function (e) {
    e.preventDefault();

    const targetID = this.getAttribute("href");
    const targetElement = document.querySelector(targetID);

    targetElement.scrollIntoView({
      behavior: "smooth",
      block: "start",
    });
  });
});

let lastScrollTop = 0;
const header = document.querySelector("header");

window.addEventListener("scroll", () => {
  const scrollTop = window.pageYOffset || document.documentElement.scrollTop;

  if (scrollTop > lastScrollTop) {
    // Scroll down
    header.classList.add("hidden");
  } else {
    // Scroll up
    header.classList.remove("hidden");
  }

  lastScrollTop = Math.max(scrollTop, 0); // Ensure lastScrollTop is non-negative
});
