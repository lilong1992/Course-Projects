This is the version 2 of the sally's stash game. Player are able to set either Player A or Player B or both as a bot. 

At the beginning the human player will be asked to put several stacks at different position with the orientation the player choose.

When the game begins, the human player can take the one of the following actions:

M: move a placed stack to another position and orientation

D: dig a square of opponent's board

S; scan a 3x3 diamond area of opponent's board, the system will tell how many squares from each pattern

Who finds opponent's all gold first will win.

Code explanation:

The test cases covers 100%. The overal idea is a "Game" class to control the flow of the game e.g. setpatterns, running, ending. "Display" calss to print different messages, its methods are all static, so whenever you have to print an error message or a user feedback message, just call the method from Display. "Inputcheck" class is used to check if the input is valid. "Player" class stores the key information of the player, and has a ton of methods to work on the "Board" data. A player has two boards, ex_board and hid_board. The ex_board is explicitly seen to the player itself. The hid_board is used to be shown to the opponent. "NPCPlayer" extends "Player" because they have the same data and can do the same operation on the Board. However, the NPC doesn't need any input. In my dumb NPCPlayer class, it just random put stacks and random guess where the gold is. The "Pattern" interface will be implemented by different types of stacks. Each pattern requires there to be a mode (orientation), and a BoardCoord array for each orientation. For example, the GreenStack has two modes 'H' and 'V', the 'H' is [(0,0),(0,1)]. This BoardCoord array tells the relative position to the setting point. 

