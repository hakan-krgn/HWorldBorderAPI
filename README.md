**Example Code:**

```java
HWorldBorder hWorldBorder = WorldBorderAPI.getWorldBorderManager().setCenter(player.getLocation()).setColor(HBorderColor.RED).setSize(20).setDamageAmount(0).create();
hWorldBorder.send(player);
new BukkitRunnable() {
    @Override
    public void run() {
        hWorldBorder.setColor(HBorderColor.BLUE);
        hWorldBorder.update();
    }
}.runTaskLater(ClaimPlugin.getInstance(), 20 * 5);
new BukkitRunnable() {
    @Override
    public void run() {
        hWorldBorder.setColor(HBorderColor.GREEN);
        hWorldBorder.update();
    }
}.runTaskLater(ClaimPlugin.getInstance(), 20 * 10);
new BukkitRunnable() {
    @Override
    public void run() {
        hWorldBorder.remove(player);
    }
}.runTaskLater(ClaimPlugin.getInstance(), 20 * 15);
```