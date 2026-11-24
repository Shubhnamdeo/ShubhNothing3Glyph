package com.alcyon.glyphanki.anki

import android.content.Context
import com.alcyon.glyphanki.parser.HtmlParser

data class AnkiCard(
    val noteId: Long,
    val cardOrd: Int,
    val gradeable: Boolean = true,
    val frontHtml: String,
    val backHtml: String,
    val frontAudioPath: String?,
    val backAudioPath: String?
) {
    fun getFrontText(context: android.content.Context): String {
        val prefs = context.getSharedPreferences("display_prefs", android.content.Context.MODE_PRIVATE)
        val reverse = prefs.getBoolean("reverse_mode", false)
        return if (reverse) HtmlParser.extractDefinitionSummary(backHtml) else HtmlParser.extractTextFromHtml(frontHtml)
    }
    fun getBackText(context: android.content.Context): String {
        val prefs = context.getSharedPreferences("display_prefs", android.content.Context.MODE_PRIVATE)
        val reverse = prefs.getBoolean("reverse_mode", false)
        return if (reverse) HtmlParser.extractTextFromHtml(frontHtml) else HtmlParser.extractDefinitionSummary(backHtml)
    }
    fun getFrontAudio(context: android.content.Context): String? {
        val prefs = context.getSharedPreferences("display_prefs", android.content.Context.MODE_PRIVATE)
        val reverse = prefs.getBoolean("reverse_mode", false)
        return if (reverse) backAudioPath else frontAudioPath
    }
    fun getBackAudio(context: android.content.Context): String? {
        val prefs = context.getSharedPreferences("display_prefs", android.content.Context.MODE_PRIVATE)
        val reverse = prefs.getBoolean("reverse_mode", false)
        return if (reverse) frontAudioPath else backAudioPath
    }
}