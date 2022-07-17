package dev.goobar.androidstudyguide.studyguide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.data.Topic
import dev.goobar.androidstudyguide.databinding.ItemTopicBinding

class TopicViewHolder(private val binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root) {

  fun bindTopic(topic: Topic) {
    binding.topicTitle.text = topic.title
    binding.topicContent.text = topic.content
    binding.topicCategories.text = topic.categories.joinToString(", ")
  }
}

object TopicDiffUtil : DiffUtil.ItemCallback<Topic>() {
  override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
    return oldItem == newItem
  }
}

class StudyGuideListAdapter : ListAdapter<Topic, TopicViewHolder>(TopicDiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
    val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return TopicViewHolder(binding)
  }

  override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
    val topic = getItem(position)
    holder.bindTopic(topic)
  }
}