# Soda Machine Application

This is a Java-based Soda Machine application that simulates a coin-operated soda vending machine. The machine accepts VND notes, allows users to select products, provides refunds upon request, and has a promotional scheme for free products based on purchase history.

## Features

1. Accepts notes of 10,000, 20,000, 50,000, 100,000, and 200,000 VND.
2. Allows the user to select products: Coke (10,000 VND), Pepsi (10,000 VND), Soda (20,000 VND).
3. Allows the user to receive a refund by canceling the request.
4. Releases the selected product and returns any remaining change.
5. Promotion scheme:
    - If there are 3 consecutive purchases of the same product, the user has a 10% chance to receive a product for free.
    - The limited budget for the program is 50,000 VND per day.
    - If the limit is not reached for a day, the win rate for the next day will increase by 50%.

## Prerequisites

- Java Development Kit (JDK) installed on your machine.
- An IDE or text editor to view and edit the code (optional but recommended).

## How to Run
Run from the main class in src/main file
