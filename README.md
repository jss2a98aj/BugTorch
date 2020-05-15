# BugTorch

BugTorch is a mod for Minecraft 1.7.10. It is called BugTorch because reasons. It mostly fixes bugs.

## Requirements

SpongeMixins
https://github.com/GTNewHorizons/SpongeMixins

## What it does
Backports a small handful of features that don't add new things, fixes some bugs, and does other stuff. Everything can be toggled in the configs. The list below is missing serveral changes from update 1.0.2
<table width="100%">
  <thead>
    <tr>
      <th width="50%">Backported features</th>
      <th width="15%">From MC</th>
      <th width="20%">Fixes</th>
      <th width="15%">Mixin</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Trapdoors no longer require attachment blocks</td>
      <td valign="top">1.9</td>
      <td valign="top">N/A</td>
      <td valign="top">No</td>
    </tr>
    <tr>
      <td valign="top">Cobwebs can be collected with Shears without Silk Touch</td>
      <td valign="top">1.9</td>
      <td valign="top">MC-93001</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Dead Bushes drop 0-2 Sticks</td>
      <td valign="top">1.9</td>
      <td valign="top">N/A</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Minecarts with TNT explode when hit by fire arrows</td>
      <td valign="top">1.8</td>
      <td valign="top">MC-8987</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Ender Pearls can be thrown in creative mode</td>
      <td valign="top">1.9</td>
      <td valign="top">MC-438</td>
      <td valign="top">Yes</td>
    </tr>
  </tbody>
</table>

<table width="100%">
  <thead>
    <tr>
      <th width="50%">Bugfixes</th>
      <th width="15%">From MC</th>
      <th width="20%">Fixes</th>
      <th width="15%">Mixin</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Fire Charges have the correct use sound</td>
      <td valign="top">1.8</td>
      <td valign="top">MC-1831</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Lava will only hiss when mixing with water</td>
      <td valign="top">1.8</td>
      <td valign="top">MC-32301</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Pumpkins and Jack o'Lanterns can be placed without a solid block below them</td>
      <td valign="top">1.13</td>
      <td valign="top">MC-1947</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Shearing a block will not give drops in addition to itself</td>
      <td valign="top">N/A</td>
      <td valign="top">N/A</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Shears will take damage when used to mine any block</td>
      <td valign="top">1.9</td>
      <td valign="top">MC-8180</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Sign update packets for signs in unloaded chunks will not send chat messages</td>
      <td valign="top">1.9</td>
      <td valign="top">MC-3564</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Stone Monster Eggs only spawn one Silverfish when broken</td>
      <td valign="top">1.8</td>
      <td valign="top">MC-31081</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Village paths will not have flowers or grass on top of them</td>
      <td valign="top">1.10</td>
      <td valign="top">MC-3437</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Zombies will seige villages that are large enough at night</td>
      <td valign="top">1.8</td>
      <td valign="top">MC-7432, MC-7488</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Wells in desert villages will use the correct material</td>
      <td valign="top">1.8</td>
      <td valign="top">MC-32514</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Non-layered snow blocks will no longer randomly tick</td>
      <td valign="top">1.14</td>
      <td valign="top">MC-88097</td>
      <td valign="top">No</td>
    </tr>
  </tbody>
</table>

<table width="100%">
  <thead>
    <tr>
      <th width="85%">Performance improvements</th>
      <th width="15%">Mixin</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Speed up initial world loading by not waiting for as many chunks to load</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Dropped item stacking checks are much faster for full stacks</td>
      <td valign="top">Yes</td>
    </tr>
    <tr>
      <td valign="top">Broken chests don't split apart dropped item stacks</td>
      <td valign="top">Yes</td>
    </tr>
  </tbody>
</table>

<table width="100%">
  <thead>
    <tr>
      <th width="85%">Tweaks</th>
      <th width="15%">Mixin</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Override the port used when opening singleplayer to LAN (1024 to 49151, defaults to 25565)</td>
      <td valign="top">Yes</td>
    </tr>
  </tbody>
</table>

<table width="100%">
  <thead>
    <tr>
      <th width="85%">Bugfixes for other mods (if present)</th>
      <th width="15%">Mixin</th>
    </tr>
  </thead>
  <tbody>
      <tr>
      <td valign="top">Registers Thaumcraft Greatwood and Silverwood leaves as treeLeaves</td>
      <td valign="top">No</td>
    </tr>
    <tr>
      <td valign="top">Registers Thaumcraft Thaumium Blocks as blockThaumium</td>
      <td valign="top">No</td>
    </tr>
    <tr>
      <td valign="top">Registers Thaumcraft Greatwood and Silverwood stairs as stairWood</td>
      <td valign="top">No</td>
    </tr>
    <tr>
      <td valign="top">The back of Gany's Surface trapdoors will use the correct texture</td>
      <td valign="top">Yes</td>
    </tr>
  </tbody>
</table>

## Modpacks
Yes, you can use this mod in your modpack.
