# FutureBans
> [!NOTE]
> Diese Codebase ist zum größten Teil noch auf dem letzten Stand von Tobi's BanSystem.
> Ich habe allerdings angefangen, meine ersten größeren Änderungenen zu machen.

Ein umfangreiches Moderationssystem für Minecraft Server. Umfasst viele Funktionen und ist einfach zu bedienen.

## Funktionen
- Ban, Mute & Kick
- Chatfilter
- Chatverzögerung
- IP Logging
- Presets
- Moderationsverlauf
- VPN-Check
- Geyser-Unterstützung

## Anforderungen
- **mindestens Java 8**
- **Spigot, Paper, Waterfall, oder Velocity**
- MySQL Datenbank (optional)

## Installation
1. Lade dir das Plugin herunter.
2. Lege die Datei in den Plugins-Ordner 
   - Solltest du Waterfall oder Velocity verwenden, installiere das Plugin **nicht auf den Unterservern**.
3. Starte deinen Server.
4. Konfiguriere das Plugin in der `config.yml`.

> [!NOTE]
> <details>
> <summary>Ab der Version 1.19.1 werden auf Waterfall & Velocity weitere Schritte benötigt. (aufklappbar)</summary>
> 
> Waterfall:
> 1. Die Option `signdChatBypass` in der `config.yml` auf `true` stellen.
> 2. Die beigelegte Datei `BanSystem-SpigotChatAdapter-X.X-SNAPSHOT.jar` **auf allen Unterservern** installieren.
> 
> Velocity:
> 1. Das Plugin [SigndVelocity](https://modrinth.com/plugin/signedvelocity) für **Velocity und alle Unterserver-Varianten** herunterladen, die du verwendest.
> 2. Die entsprechenden Dateien **auf Velocity und allen Unterservern** installieren.
> 
> </details>

## Support
Bitte erstelle eine GitHub Issue oder komme auf unseren Discord Server: https://discord.gg/8sZrtEGXSa

## Presets
- Presets funktionieren über IDs. Es lassen sich alle IDs mithilfe von `/ban` auflisten.
- IDs können sowohl per Befehl, als auch in der `config.yml` bearbeitet werden.
- Die Zeiten für Bestrafungen werden derzeit in Sekunden berechnet (heißt ein Tag = `86400 Sekunden`).
- Für alle IDs lassen sich Level festlegen - das Level wird mit jedem Mal erhöht, mit dem der Spieler mit der selben ID gebannt wird. So lassen sich Bestrafungen z.B. automatisch verlängern.
- Falls ein Spieler bei einer ID bereits alle Level erreicht hat, wird das letzte verfügbare Level erneut verwendet.

<details>
<summary>Beispiel für Presets (aufklappbar)</summary>

```yaml
IDs:
  '1': # ID
    reason: Cheating # Grund, der dem Spieler angezeigt wird
    onlyAdmins: true # Ob zusätzliche Rechte benötigt werden, um diese ID zu verwenden
    lvl:
      '1': # Level 1
        type: NETWORK # Ob man Global (NETWORK), oder nur im CHAT gebannt wird
        duration: 604800 # Dauer des Banns in Sekunden (hier 7 Tage)
      '2':
        type: NETWORK
        duration: -1 # -1 bedeutet permanent
  '2':
     reason: Chatbann
     onlyAdmins: false
     lvl:
        '1': # Nur ein Level, Chatbann dauert immer 7 Tage an
           type: CHAT
           duration: 604800
```

Der Befehl würde dann so aussehen: `/ban <Spieler> <ID>`, z.B. `/ban LeKoopa92 1`.

</details>

## IP Handling
Wenn ein Spieler gebannt wird, wird die IP des Spielers in der Datenbank gespeichert. Sollte ein Spieler mit derselben IP dem Server beitreten, werden alle berechtigten Teammitglieder im Chat benachrichtigt.
Es ist möglich einzustellen, dass diese Spieler automatisch gebannt werden - dies empfehlen wir allerdings nicht.

## VPNAPI
Der VPN-Check von FutureBans verwendet [VPNAPI](https://vpnapi.io/).
<br>
Sollten deinem Server mehr als 100 Spieler am Tag beitreten, registriere dich dort und trage deinen API-Key in der `config.yml` ein.

## Befehle und Rechte
<details>
<summary>Aufklappen zum einsehen</summary>

### Berechtigungen für Befehle

| Befehl                                                               | Berechtigung                    |
|----------------------------------------------------------------------|---------------------------------|
| `/bansystem`                                                         | `bansys.bansys`                 |
| `/bansystem reload`                                                  | `bansys.reload`                 |
| `/bansystem ids create <ID> <OnlyAdmins> <duration> <Type> <reason>` | `bansys.ids.create`             |
| `/bansystem ids delete <ID>`                                         | `bansys.ids.delete`             |
| `/bansystem ids edit <ID> add lvl <Duration> <Type>`                 | `bansys.ids.addlvl`             |
| `/bansystem ids edit <ID> remove lvl <lvl>`                          | `bansys.ids.removelvl`          |
| `/bansystem ids edit <ID> set lvlduration <lvl> <Duration>`          | `bansys.ids.setduration`        |
| `/bansystem ids edit <ID> set lvltype <lvl> <Type>`                  | `bansys.ids.settype`            |
| `/bansystem ids edit <ID> set onlyadmins <True/False>`               | `bansys.ids.setonlyadmins`      |
| `/bansystem ids edit <ID> set reason <reason>`                       | `bansys.ids.setreason`          |
| `/bansystem ids show <ID>`                                           | `bansys.ids.show`               |
| `/bansys logs show [site]`                                           | `bansys.logs.show`              |
| `/bansys logs clear`                                                 | `bansys.logs.clear`             |
| `/ban <Spieler> <ID>`                                                | `bansys.ban(.<ID>/.all/.admin)` |
| `/unban <Spieler>`                                                   | `bansys.unban`                  |
| `/unmute <Spieler>`                                                  | `bansys.unmute`                 |
| `/check <Spieler>`                                                   | `bansys.check`                  |
| `/deletehistory <Spieler>`                                           | `bansys.history.delete`         |
| `/history <Spieler>`                                                 | `bansys.history.show`           |
| `/kick <Spieler> [Grund]`                                            | `bansys.kick(.admin)`           |

### Weitere Berechtigungen

| Aktion                  | Berechtigung              |
|-------------------------|---------------------------|
| Team Benachrichtigungen | `bansys.notify`           |
| Kicks umgehen           | `bansys.kick.bypass`      |
| Banns umgehen           | `bansys.ban.bypass`       |
| Chatfilter umgehen      | `bansys.bypasschatfilter` |
| Chatverzögerung umgehen | `bansys.bypasschatdelay`  |

</details>

## Credits
- [Tobi](https://github.com/antisocialtobi) für die ursprüngliche Codebase!
- Alle [Mitwirkenden](https://github.com/JuniorWolfgamingDE/FutureBans/contributors), die zur Entwicklung beigetragen haben.
