[![CircleCI](https://circleci.com/gh/Easyisac/pong/tree/main.svg?style=svg)](https://circleci.com/gh/Easyisac/pong/tree/main)

# Pong
*Valentina Blasone, Daniele Irto, Giulia Marchiori Pietrosanti, Matteo Marturini, Isacco Zinna*

Final project for the Software Development Methods course of DSSC at the University of Trieste.

Pong is a video game inspired by table-tennis, originally developed by Allan
Alcorn and released in 1972 by Atari.
The game features two paddles and a ball moving inside a game court. Each
player moves a paddle up and down their side of the screen, trying to hit the ball
back and forth. Players earn one point when the opponent fails to hit the ball.
The objective is to be the first one to gain a pre-defined amount of points.

We developed the game following Test-driven development practices.

Principal elements: 

- Game court: 2D area inside which ball and paddles move
- Ball: moves freely inside the game court, bouncing off borders and paddles;
if it goes past a paddle, the opponent scores a point
- Paddles: move up and down on a vertical track
- Players: each one controls one paddle and tries to score a goal against the
opponent

Game modes:
- Single player: user moves one paddle while a bot moves the other
- Two players: one user moves his paddle using the UP and DOWN keys while
the other moves his paddle using W and S keys
