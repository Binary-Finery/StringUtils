package com.spencerstudios.stringutils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.hide()

        val i = intent
        val action = i.action
        val type = i.type

        if (Intent.ACTION_SEND == action && type != null) {
            if ("text/plain" == type) {
                val text = i.getStringExtra(Intent.EXTRA_TEXT)
                if (text != null) {
                    if (text.isNotEmpty()) {
                        src.setText(text)
                    }
                }
            }
        }

        button.setOnClickListener {
            val valid = et_replace.text.isNotEmpty() && src.text.isNotEmpty()
            if (valid) {
                out.text = src.text.toString().replace(et_replace.text.toString(), et_with.text.toString())
            }
        }

        share.setOnClickListener {
            if (out.text.isNotEmpty()) {
                Utils.share(out.text as String, this@MainActivity)
            }
        }

        copy.setOnClickListener {
            if (out.text.isNotEmpty()) {
                Utils.copy(out.text as String, this@MainActivity)
            }
        }

        paste.setOnClickListener {
            if (Utils.paste(this@MainActivity).isNotEmpty()) {
                src.setText(Utils.paste(this@MainActivity))
            }
        }
        src_clear.setOnClickListener {
            src.setText("")
        }
        out_clear.setOnClickListener {
            out.text = ""
        }
    }
}


