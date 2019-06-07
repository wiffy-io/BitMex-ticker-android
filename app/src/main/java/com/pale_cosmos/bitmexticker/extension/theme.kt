package com.pale_cosmos.bitmexticker.extension

import com.pale_cosmos.bitmexticker.R
import com.pale_cosmos.bitmexticker.model.Util


        fun get_table_in(): Int {
            if (Util.dark_theme)
                return R.color.dark_table_in
            else return R.color.light_table_in
        }

        fun get_table_out(): Int {
            if (Util.dark_theme)
                return R.color.dark_table_out
            else return R.color.light_table_out
        }

        fun get_title(): Int {
            if (Util.dark_theme)
                return R.color.dark_title
            else return R.color.light_title
        }

        fun get_title2(): Int {
            if (Util.dark_theme)
                return R.color.dark_title2
            else return R.color.light_title2
        }
