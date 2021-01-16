# SpeedySalamanders

## Quick Links
- [Kanban](https://github.com/nathanielCherian/SpeedySalamanders/projects/1) Includes cards/tickets
- [Issues](https://github.com/nathanielCherian/SpeedySalamanders/issues)
- [Project Plan](https://docs.google.com/document/d/1mY7egnD32HIzg7C8C9wMr7rFPewVq4cXQqh_mbIy8mc/edit?usp=sharing)
- [Contributions](https://github.com/nathanielCherian/SpeedySalamanders/graphs/contributors)
- [About](https://github.com/nathanielCherian/SpeedySalamanders#about)
- [Contributers](https://github.com/nathanielCherian/SpeedySalamanders#contributers)
## Completed Goals
- [Issues](https://github.com/nathanielCherian/SpeedySalamanders/issues) bug fixes with image rendering, fixed with resizing and "cleaning"
- [AP FRQ](https://github.com/nathanielCherian/SpeedySalamanders/tree/master/src/APFRQ) implementation Units 3, 4, & 5
- [New Objects](https://github.com/nathanielCherian/SpeedySalamanders/tree/master/src/Game/Objects) fire particles and dog (will become interactive)
- [Multiplayer](https://github.com/nathanielCherian/SpeedySalamanders/tree/master/src/Game/Multiplayer) functionality working properly

## Usage
Install the following dependencies with Maven
- ```com.amazonaws:aws-java-sdk-dynamodb:1.11.911```
- ```com.googlecode.json-simple:json-simple:1.1.1```

The project also requires Java 15

To start the game run ```Home.Main``` located within the ```Game``` package.

*Note at this stage of development, the server is hardcoded into the system, this can cause connection issues as the public game server is not alive 24/7. To play singleplayer change [this ip](https://github.com/nathanielCherian/SpeedySalamanders/blob/master/src/Game/Board.java#L26) variable to false before runtime. To run a development server yourself run [this](https://github.com/nathanielCherian/SpeedySalamanders/blob/master/src/Game/Multiplayer/ServerMain.java#L23) static method then change [this](https://github.com/nathanielCherian/SpeedySalamanders/blob/master/src/Game/Board.java#L42) to `127.0.0.1`*


## About
Our project will have 2D/3D games using databases. 
- [ ] 2D with stages
- [ ] 3D running area

## Contributers
| GitHub Username | Person |
| --- | --- |
| [@nathanielCherian](https://github.com/VihanJ) | Nathaniel Cherian |
| [@jettKim](https://github.com/JettKim) | Jett Kim |
| [@alextitov19](https://github.com/alextitov19) | Alex Titov |
| [@bennyherrick](https://github.com/bennyherrick) | Benny Herrick |

# 2D
For our project, 2D games like platformers with stages seems appropriate and we will try to create unique 2D games with stages stored in a database.
# 3D
Since the nature of 3D projects naturally makes them harder to code, we will try to sitck to a 3D running game with auto obstacles.
# Contributions
- Code monitored through Github 
  - Version Control
  - Readme/Kanban board
  - Commits/Lines/Deletions
- Journal work
  - Version History
  - Collaboration
  - Quality/Effort
