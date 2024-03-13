import * as THREE from "https://threejs.org/build/three.module.js";

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(
  75,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
);
const renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);



const geometry = new THREE.BoxGeometry();
const material = new THREE.MeshBasicMaterial({ color: 0xff0000 });

const geometryBullet = new THREE.SphereGeometry();
const materialBullet = new THREE.MeshBasicMaterial({ color: 0x00ff00 });

const ship1 = new THREE.Mesh(geometry, material);
const ship2 = new THREE.Mesh(geometry, material);

ship1.position.set(0, 5, 0);
ship2.position.set(0, -5, 0);

scene.add(ship1);
scene.add(ship2);

camera.position.z = 10;

const shootButton = document.getElementById("shootButton");
let shooting = false;

document.addEventListener("keydown", (event) => {
  switch (event.key) {
    case "ArrowUp":
      ship1.position.y += 0.4;
      break;
    case "ArrowDown":
      ship1.position.y -= 0.4;
      break;
    case "ArrowLeft":
      ship1.position.x -= 0.4;
      break;
    case "ArrowRight":
      ship1.position.x += 0.4;
      break;
    default:
      break;
  }
});

shootButton.addEventListener("click", () => {
  shooting = true;
});

function animate() {
  requestAnimationFrame(animate);

  if (shooting) {
    const bullet = new THREE.Mesh(geometry, material);
    bullet.position.copy(ship1.position);
    scene.add(bullet);

    // Simulate bullet motion (this is a simple linear motion, you may need more sophisticated logic)
    const bulletSpeed = new THREE.Vector3(0, 1, 0);
    bulletSpeed.applyQuaternion(ship1.quaternion);
    bulletSpeed.multiplyScalar(0.5); // Adjust the speed as needed

    function moveBullet() {
      bullet.position.add(bulletSpeed);
      if (bullet.position.y > 10) {
        scene.remove(bullet);
        shooting = false;
      }
    }

    const bulletAnimation = new THREE.AnimationClip("bulletAnimation", -1, [
      new THREE.VectorKeyframeTrack(
        ".position",
        [0, 1],
        [
          bullet.position.x,
          bullet.position.y,
          bullet.position.z,
          bullet.position.x + bulletSpeed.x,
          bullet.position.y + bulletSpeed.y,
          bullet.position.z + bulletSpeed.z,
        ]
      ),
    ]);
    const bulletMixer = new THREE.AnimationMixer(bullet);
    const bulletAction = bulletMixer.clipAction(bulletAnimation);
    bulletAction.play();

    function animateBullet() {
      requestAnimationFrame(animateBullet);
      bulletMixer.update(0.05); // Use a time delta here if needed
      moveBullet();
    }

    animateBullet();
  }

  renderer.render(scene, camera);
}

animate();
