const canvas = document.getElementById('gameCanvas');
const ctx = canvas.getContext('2d');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const ship1 = {
  x: canvas.width / 2,
  y: canvas.height - 50,
  width: 30,
  height: 50,
  speed: 5,
  shooting: false,
};

const ship2 = {
  x: canvas.width / 2,
  y: 0,
  width: 30,
  height: 50,
  speed: 5,
  shooting: false,
};

const bullets = [];

function drawShip(x, y, width, height, color) {
  ctx.fillStyle = color;
  ctx.fillRect(x, y, width, height);
}

function draw() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  drawShip(ship1.x, ship1.y, ship1.width, ship1.height, 'blue');
  drawShip(ship2.x, ship2.y, ship2.width, ship2.height, 'red');

  for (let i = 0; i < bullets.length; i++) {
    ctx.fillStyle = 'black';
    ctx.fillRect(bullets[i].x, bullets[i].y, 5, 5);
  }
}

function update() {
  if (ship1.shooting) {
    bullets.push({ x: ship1.x + ship1.width / 2, y: ship1.y });
    ship1.shooting = false;
  }

  if (ship2.shooting) {
    bullets.push({ x: ship2.x + ship2.width / 2 - 5, y: ship2.y + ship2.height });
    ship2.shooting = false;
  }

  for (let i = 0; i < bullets.length; i++) {
    bullets[i].y += 5;

    // Check for bullet collisions or out-of-bounds
    if (
      bullets[i].x < 0 ||
      bullets[i].x > canvas.width ||
      bullets[i].y < 0 ||
      bullets[i].y > canvas.height
    ) {
      bullets.splice(i, 1);
      i--;
    }
  }
}

function gameLoop() {
  draw();
  update();
  requestAnimationFrame(gameLoop);
}

document.addEventListener('keydown', (event) => {
  switch (event.key) {
    case 'ArrowUp':
      ship1.y -= ship1.speed;
      break;
    case 'ArrowDown':
      ship1.y += ship1.speed;
      break;
    case 'ArrowLeft':
      ship1.x -= ship1.speed;
      break;
    case 'ArrowRight':
      ship1.x += ship1.speed;
      break;
    default:
      break;
  }
});

document.getElementById('shootButton').addEventListener('click', () => {
  ship1.shooting = true;
});

document.addEventListener('keydown', (event) => {
  if (event.key === ' ') {
    ship2.shooting = true;
  }
});

window.addEventListener('resize', () => {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
});

gameLoop();
