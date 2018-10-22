package com.spencerstudios.stringutils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast

class Utils {

    companion object {

        fun share(text: String, ctx: Context) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, text)
            sendIntent.type = "text/plain"
            ctx.startActivity(sendIntent)
        }

        fun copy(text: String, ctx: Context) {
            val cbm = ctx.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val cd = ClipData.newPlainText("data", text)
            cbm.primaryClip = cd
            Toast.makeText(ctx, "Copied to clipboard",Toast.LENGTH_SHORT).show()
        }

        fun paste(ctx: Context): String {
            val cbm = ctx.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val cd = cbm.primaryClip
            if (cd.itemCount > 0) {
                val item = cd.getItemAt(0)
                return item.text.toString()
            }
            return ""
        }
    }
}
