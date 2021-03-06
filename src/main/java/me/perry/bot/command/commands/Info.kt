package me.perry.bot.command.commands

import me.perry.bot.command.Command
import me.perry.bot.utils.JsonUtil.parse
import me.perry.bot.utils.JsonUtil.parseArray
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

/**
 * @author perry.
 * @since 2/9/2022.
 */
class Info : Command("Info", ":information_source:", "Tells you info about 2b au.") {

    override fun onExecute(event: MessageReceivedEvent): Boolean {
        val embedBuilder = EmbedBuilder()
        val memory = parseArray("https://api.2b2t.com.au/v1/server", "performance", "totalMemory")
        embedBuilder.setTitle("2b2t AU Server Info")
        embedBuilder.setColor(
            Color(
                8,
                185,
                20,
                255
            )
        )
        embedBuilder.addField(
            ":flag_au: - Name",
            parse("https://api.2b2t.com.au/v1/server", "name"),
            false
        )
        embedBuilder.addField(
            ":star: - MOTD",
            parse("https://api.2b2t.com.au/v1/server", "motd"),
            false
        )
        embedBuilder.addField(
            ":arrow_forward: - Players",
            parse("https://api.2b2t.com.au/v1/server", "online"),
            false
        )
        embedBuilder.addField(
            ":desktop: - Version",
            parse("https://api.2b2t.com.au/v1/server", "version"),
            false
        )
        embedBuilder.addField(
            ":clock1: - Uptime",
            parse("https://api.2b2t.com.au/v1/server", "uptime"),
            false
        )
        embedBuilder.addField(
            ":computer: - TPS",
            parseArray("https://api.2b2t.com.au/v1/server", "performance", "tps"),
            false
        )
        embedBuilder.addField(
            ":computer: - Memory",
            memory.substring(0, memory.length - 6) + " MB",
            false
        )
        embedBuilder.addField(
            ":computer: - CPU(s)",
            parseArray("https://api.2b2t.com.au/v1/server", "performance", "cpus"),
            false
        )
        event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
        return true
    }
}