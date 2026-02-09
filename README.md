# myCalculator

A simple calculator application built with **Java Swing**, created as my first project to practice **Object-Oriented Programming (OOP)** principles.

---

## About the Project
This calculator was developed as part of my learning journey as a freshman undergraduate in Software Engineering at the *University of Kurdistan Hewler (UKH)*.  
The goal was not just to build a working calculator, but to explore OOP concepts such as **abstract classes, interfaces, encapsulation, and separation of concerns**.

---

## Features
- Graphical User Interface (GUI) built with Swing
- Handles basic arithmetic operations (+, −, ×, ÷)
- Additional operations:
  - Square root
  - Percentage
  - Sign flip (+/−)
- Clear separation between:
  - **Values (Abstract Class):** Stores private fields and state with getters/setters
  - **Process (Interface):** Defines calculation logic
  - **CalculatorGUI (Class):** Manages the user interface and integrates logic
- Encapsulation: Almost all variables are private with getters/setters
- Input string exposed for GUI display (pragmatic design choice)
- Error handling

---

## Design Notes
- The GUI styling and layout were assisted by AI tools, while I focused on the **logic, integration, and OOP structure**.  
- Think of it like a web project: I handled the *HTML + JS (logic and structure)*, while AI helped with the **CSS (styling)**.  
- Intentional quirks: The `2+2=1` case is a playful reference to a Kurdish saying, showing how logic can be customized.

---

## Why This Project Matters
- Demonstrates early practice with OOP principles
- Shows ability to integrate GUI with backend logic
- Reflects my growth as a developer at the start of my academic journey

---

## Installation & Usage

### Run from source
Compile and run using `javac` and `java`:
```bash
# Navigate to project root
cd Calculator

# Compile
javac -d dist src/calculator/*.java

# Run
java -cp dist calculator.Main
