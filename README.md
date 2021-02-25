**Example Code:**

```java
Player player=Bukkit.getPlayer("playerName");
        HWorldBorder hWorldBorder=WorldBorderAPI.getWorldBorderManager().setSize(16).setCenter(player.getLocation()).setDamageBuffer(0).setWarningDistance(0).setDamageAmount(0).setWarningTime(0).create();
        hWorldBorder.send(player);
        new BukkitRunnable(){
@Override
public void run(){
        hWorldBorder.remove(player);
        }
        }.runTaskLater(plugin,200);
```
