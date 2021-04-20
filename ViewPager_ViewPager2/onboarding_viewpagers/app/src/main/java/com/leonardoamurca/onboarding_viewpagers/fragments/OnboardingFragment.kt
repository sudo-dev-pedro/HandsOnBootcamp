package com.leonardoamurca.onboarding_viewpagers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.leonardoamurca.onboarding_viewpagers.R

class OnboardingFragment : Fragment() {
    private var title: String? = null
    private var description: String? = null
    private var imageResource = 0
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var image: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = requireArguments().getString(TITLE)
            description = requireArguments().getString(DESCRIPTION)
            imageResource = requireArguments().getInt(IMAGE_RESOURCE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootLayout: View =
            inflater.inflate(R.layout.fragment_onboarding1, container, false)

        tvTitle = rootLayout.findViewById(R.id.text_onboarding_title)
        tvDescription = rootLayout.findViewById(R.id.text_onboarding_description)
        image = rootLayout.findViewById(R.id.image_onboarding)

        tvTitle.text = title
        tvDescription.text = description
        image.setAnimation(imageResource)

        return rootLayout
    }

    companion object {
        private const val TITLE = "TITLE"
        private const val DESCRIPTION = "DESCRIPTION"
        private const val IMAGE_RESOURCE = "IMAGE_RESOURCE"

        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int
        ): OnboardingFragment {
            val fragment = OnboardingFragment()
            fragment.arguments = bundleOf(
                TITLE to title,
                DESCRIPTION to description,
                IMAGE_RESOURCE to imageResource
            )

            return fragment
        }
    }
}