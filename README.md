## TNT Detector Mod

The TNT Detector mod is a Minecraft Fabric mod that notifies admins when a player sets fire to TNT with Flint and Steel or Fire Charge.

### Features

- Detects when players ignite TNT blocks.
- Notifies admins with the player's name and the coordinates of the ignited TNT block.
- Uses Fabric mixins to inject code into the TNT block's ignition method.

### Requirements
- Minecraft version: 1.20.1
- Fabric Loader version: 0.14.11
- Fabric API version: 0.83.0+1.20.1

### Usage

- Igniting TNT
  - Obtain Flint and Steel or Fire charger in game
  - Right-click on a TNT block 
  - Admins will receive a notification in the chat

- Notification format
  - Player [player nickname] ignited TNT at [x]. [y], [z]

