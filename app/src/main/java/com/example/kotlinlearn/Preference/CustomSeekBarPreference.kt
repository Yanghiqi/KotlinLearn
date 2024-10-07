package com.example.kotlinlearn.Preference


import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlearn.R

class CustomSeekBarPreference(context: Context, attrs: AttributeSet) : Preference(context, attrs) {

    private var currentValue: Int = 0
    private var maxValue: Int = 100

    init {
        widgetLayoutResource = R.layout.preference_seekbar
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        val seekBar = holder.findViewById(R.id.seekbar) as SeekBar
        val valueText = holder.findViewById(R.id.seekbar_value) as TextView

        seekBar.max = maxValue
        seekBar.progress = currentValue

        valueText.text = currentValue.toString()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentValue = progress
                valueText.text = progress.toString()
                persistInt(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getInt(index, 0)
    }

    override fun onSetInitialValue(defaultValue: Any?) {
        currentValue = getPersistedInt(defaultValue as? Int ?: 0)
    }
}
