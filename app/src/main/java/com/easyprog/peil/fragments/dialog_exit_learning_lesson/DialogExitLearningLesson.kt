package com.easyprog.peil.fragments.dialog_exit_learning_lesson

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.easyprog.peil.databinding.DialogExitLearningLessonBinding

class DialogExitLearningLesson(
    private val listener: DialogListener
) : DialogFragment() {

    private var _binding: DialogExitLearningLessonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogExitLearningLessonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.textDialogPositive.setOnClickListener {
            dismiss()
        }
        binding.textDialogNegative.setOnClickListener {
            listener.onDialogNegativeClick(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

//    override fun onStart() {
//        super.onStart()
//        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
//        val height = (resources.displayMetrics.heightPixels * 0.85).toInt()
//        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
//    }

}