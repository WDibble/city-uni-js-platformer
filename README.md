# 🎮 2D Java Platformer Demo
<div align="center">
![Game Preview](https://github.com/WDibble/choose-adventure-game/blob/main/src/assets/preview.gif)

A classic-style 2D platformer built with Java and the CityEngine physics framework.

<img alt="Java" src="https://img.shields.io/badge/Java-8+-red.svg">
<img alt="CityEngine" src="https://img.shields.io/badge/CityEngine-1.0-blue.svg">
<img alt="License" src="https://img.shields.io/badge/License-MIT-green.svg">
</div>

## 🎮 Game Overview
A challenging 2D platformer featuring precise physics-based movement, collectible items, and multiple levels. Navigate through increasingly difficult stages while avoiding enemies and collecting power-ups. The game showcases core platformer mechanics with a focus on smooth controls and engaging level design.

## ✨ Features
- Physics-Based Movement: Smooth player controls using CityEngine physics
- Multiple Levels: Progressive difficulty across various stages
- Power-ups: Collect mushrooms to enhance jump height
- Enemy AI: Dynamic enemy movement and interaction
- Score System: Track points and completion time
- Health System: Strategic health management
- Custom Graphics: Hand-crafted sprite animations and backgrounds

## 🛠️ Technical Details

### Architecture
- Model-View-Controller: Clean separation of game logic, rendering, and input
- Entity Component System: Modular game object management
- Collision Detection: Precise hitbox-based interactions
- State Management: Robust game state handling

### Core Classes
```
game/
├── Game.java          # Main game initialization
├── GameLevel.java     # Level management
├── Player1.java       # Player mechanics
├── Background.java    # Graphics rendering
└── Controller1.java   # Input handling
```


## 🚀 Getting Started
1. Clone the repository:
```bash
git clone https://github.com/WDibble/city-uni-js-platformer
```
2. Add <kbd>CityEngine.jar</kbd> to your project libraries
3. Run the main class:
```bash
java -cp ".:CityEngine.jar" game.Game
```

## 🎮 Controls
- <kbd>W</kbd> - Jump
- <kbd>A</kbd> - Move Left
- <kbd>D</kbd> - Move Right
- <kbd>SPACE</kbd> - Fire (when available)
- <kbd>ESC</kbd> - Pause Menu

## 🔧 Development
The project demonstrates these programming concepts:
- Object-Oriented Design
- Physics Engine Integration
- Collision Detection
- Game State Management
- Event Handling
- Graphics Rendering

## 📈 Future Improvements
- Additional levels
- More power-ups
- Enhanced particle effects
- Sound effects
- Level editor
- Save/load system

## 📝 License
This project is open source and available under the MIT License.

## 🙏 Acknowledgments
- CityEngine framework for physics simulation
- Original sprite and background artists
- Java game development community

### Built with ❤️ and ☕ by Will Dibble