package code.with.cal.moviesapp.ui.playerfragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import code.with.cal.moviesapp.databinding.FragmentPlayerBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.StyledPlayerView


class PlayerFragment : Fragment(R.layout.fragment_player), Player.Listener {

    private lateinit var playerView: StyledPlayerView
    private lateinit var player: ExoPlayer
    private lateinit var progressBar: ProgressBar


    private lateinit var binding: FragmentPlayerBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayerBinding.bind(view)
        playerView = view.findViewById(R.id.video_view)
        progressBar = view.findViewById(R.id.)


        setupPlayer()

        addMp4Files()
        if (savedInstanceState != null) {
            savedInstanceState.getInt("MediaItem").let {
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(it, seekTime)
                player.play()
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("SeekTime", player.currentPosition)
        outState.putInt("MediaItem", player.currentMediaItemIndex)
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        playerView.player = player
        player.addListener(this)
    }

    private fun addMp4Files() {
        val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
        player.addMediaItem(mediaItem)
        player.prepare()
    }

    @Deprecated("Deprecated in Java")
    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        super.onPlayerStateChanged(playWhenReady, playbackState)
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }

        }
    }


    override fun onStop() {
        super.onStop()
        player.release()
    }


}