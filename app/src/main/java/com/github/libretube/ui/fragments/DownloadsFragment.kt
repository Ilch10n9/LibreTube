package com.github.libretube.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.libretube.databinding.FragmentDownloadsBinding
import com.github.libretube.ui.adapters.DownloadsAdapter
import com.github.libretube.ui.base.BaseFragment
import com.github.libretube.util.DownloadHelper
import com.github.libretube.util.ImageHelper
import com.github.libretube.util.MetadataHelper

class DownloadsFragment : BaseFragment() {
    private lateinit var binding: FragmentDownloadsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val files = DownloadHelper.getDownloadedFiles(requireContext())

        if (files.isEmpty()) return

        val metadataHelper = MetadataHelper(requireContext())
        files.forEach {
            metadataHelper.getMetadata(it.name)?.let { streams ->
                it.metadata = streams
            }
            ImageHelper.getDownloadedImage(requireContext(), it.name)?.let { bitmap ->
                it.thumbnail = bitmap
            }
        }

        binding.downloadsEmpty.visibility = View.GONE
        binding.downloads.visibility = View.VISIBLE

        binding.downloads.layoutManager = LinearLayoutManager(context)
        binding.downloads.adapter = DownloadsAdapter(files)

        binding.downloads.adapter?.registerAdapterDataObserver(
            object : RecyclerView.AdapterDataObserver() {
                override fun onChanged() {
                    if (binding.downloads.size == 0) {
                        binding.downloads.visibility = View.GONE
                        binding.downloadsEmpty.visibility = View.VISIBLE
                    }
                    super.onChanged()
                }
            }
        )
    }
}
