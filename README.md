# HotbarFix

轻量 Paper 插件 — 玩家进服时自动将热键栏光标切换到指定位置。

A lightweight Paper plugin that auto-selects a configurable hotbar slot when players join.

## 功能 / Features

- 进服自动选中配置的热键栏槽位 / Auto-select hotbar slot on join
- config.yml 自由配置 0-8 / Configurable via config.yml
- 延迟执行，兼容 DeluxeHub / AuthMe / TrMenu 等进服物品插件
- 零外部依赖，Paper 1.21+ 即可

## 配置 / Configuration

`plugins/HotbarFix/config.yml`：

```yaml
# 0 = 第1格, 4 = 第5格(默认), 8 = 第9格
# 0 = Slot 1, 4 = Slot 5 (default), 8 = Slot 9
default-slot: 4
```

改完后重启或 `/hotbarfix reload` 生效。

## 构建 / Build

```bash
javac -cp "paper-api.jar;adventure-api.jar;adventure-key.jar" \
  -d out \
  src/main/java/com/qingyunxi/hotbarfix/HotbarFix.java

jar cf HotbarFix.jar -C out . -C src/main/resources .
```

## 许可 / License

MIT
