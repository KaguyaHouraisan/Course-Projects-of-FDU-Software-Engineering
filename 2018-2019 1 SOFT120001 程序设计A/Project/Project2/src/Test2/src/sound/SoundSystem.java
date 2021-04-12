package sound;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 用于控制游戏中的各种背景音乐及音效播放
 */
public class SoundSystem {
	private int fp;
	private double globalVol = 100;
	private double soundvolume = 100;
	private double musicVol = 100;
	private boolean musice = false;
	private boolean sounde = false;
	private boolean paused = true;
	private AudioInputStream music,pm;
	private Clip groundeds, plays, pauses, loses, rotates, moves, musics;
	private boolean worked = false;

    /**
     * 构造方法
     */
	public SoundSystem() {
	    AudioInputStream grounded, play, pause, lose, rotate, move,SelectedMusic;
		try {
			grounded = AudioSystem.getAudioInputStream(new File("src/sound/grounded.wav").getAbsoluteFile());
			play = AudioSystem.getAudioInputStream(new File("src/sound/play.wav").getAbsoluteFile());
			pause = AudioSystem.getAudioInputStream(new File("src/sound/pause.wav").getAbsoluteFile());
			lose = AudioSystem.getAudioInputStream(new File("src/sound/lose.wav").getAbsoluteFile());
			rotate = AudioSystem.getAudioInputStream(new File("src/sound/rotate.wav").getAbsoluteFile());
			move = AudioSystem.getAudioInputStream(new File("src/sound/move.wav").getAbsoluteFile());
			music = AudioSystem.getAudioInputStream(new File("src/sound/music.wav").getAbsoluteFile());
			pm = AudioSystem.getAudioInputStream(new File("src/sound/mmusic.wav").getAbsoluteFile());

			musics = AudioSystem.getClip();
			SelectedMusic = pm;
			musics.open(SelectedMusic);

			groundeds = AudioSystem.getClip();
			groundeds.open(grounded);

			plays = AudioSystem.getClip();
			plays.open(play);

			pauses = AudioSystem.getClip();
			pauses.open(pause);

			loses = AudioSystem.getClip();
			loses.open(lose);

			rotates = AudioSystem.getClip();
			rotates.open(rotate);

			moves = AudioSystem.getClip();
			moves.open(move);
			worked = true;
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Sound error");
			alert.showAndWait();
			ex.printStackTrace();
		}
	}

    /**
     * 判断音乐是否在播放
     * @return true为在播放，false为不在
     */
	public boolean isWorking() {
		return worked;
	}

    /**
     * 点击暂停时的音效
     */
	public void pause() {
		if (sounde) {
			plays(pauses);
		}
	}

    /**
     * 点击按钮时的音效
     */
	public void play() {
		if (sounde) {
			plays(plays);
		}
	}

    /**
     * 方块加速下落时的音效
     */
	public void grounded() {
		if (sounde) {
			plays(groundeds);
		}
	}

    /**
     * 游戏结束时的音效
     */
	public void lose() {
		if (sounde) {
			plays(loses);
		}
	}

    /**
     * 旋转时的音效
     */
	public void rotate() {
		if (sounde) {
			plays(rotates);
		}
	}

    /**
     * 方块左右移动时的音效
     */
	public void move() {
		if (sounde) {
			plays(moves);
		}
	}

    /**
     * 为音效音量赋初值
     * @param b 音量
     */
	public void setSounde(boolean b) {
		sounde = b;
	}

    /**
     * 建立音效音量控制器
     * @param b 音量
     */
	public void setMusice(boolean b) {
		musice = b;
		if(musics != null) {
			setMusicVol(musicVol);
			if(b) {
				musics.setFramePosition(fp);
				musics.loop(Clip.LOOP_CONTINUOUSLY);
			}else {
				fp = musics.getFramePosition();
				musics.stop();
			}
		}
	}

    /**
     * 为音乐音量赋初值
     * @param v 音量
     */
	public void setVolume(double v) {
		soundvolume = v;
	}

    /**
     * 建立背景音乐音量控制器
     * @param v 音量
     */
	public void setMusicVol(double v){
		int tar;
		if(paused) {
			tar = 120;
		}else {
			tar = 100;
		}
		if(musics != null) {
			int fp = musics.getFramePosition();
			musics.stop();
			musicVol = v;
			FloatControl volumes = (FloatControl) musics.getControl(FloatControl.Type.MASTER_GAIN);
			float range = volumes.getMaximum() - volumes.getMinimum();
			float gain = (float) ((range * (globalVol / 100) * musicVol / tar) + volumes.getMinimum());
			volumes.setValue(gain);
			musics.setFramePosition(fp);
			if(musice)
			musics.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

    /**
     * 根据点击确定不同的设置
     * @param clip 鼠标事件
     */
	private void plays(Clip clip) {
		if(clip != null &&!(clip == moves && musics.isRunning())) {
			float target;
			if(clip == groundeds) {
				target = (float) ((globalVol / 100) * (soundvolume / 100));
			}else {
				target = (float) ((globalVol / 100) * (soundvolume / 120));
			}
			FloatControl volumes = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float range = volumes.getMaximum() - volumes.getMinimum();
			float gain = range * target + volumes.getMinimum();
			volumes.setValue(gain);
			clip.setFramePosition(0);
			clip.start();
		}
	}

    /**
     * 建立全局音量控制器
     * @param v 音量
     */
	public void setGlobalVolume(double v) {
		globalVol = v;
		setMusicVol(musicVol);
		setVolume(soundvolume);
	}

    /**
     * 播放暂停时的背景音乐
     */
	public void setPauseMusic() {
		paused = true;
		musics.close();
		try {
			musics = AudioSystem.getClip();
			pm = AudioSystem.getAudioInputStream(new File("src/sound/mmusic.wav").getAbsoluteFile());
			musics.open(pm);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		if(musice) {
			setMusicVol(musicVol);
			musics.start();
		}
	}

    /**
     * 播放游戏进行时的背景音乐
     */
	public void setPlayMusic() {
		paused = false;
		musics.close();
		try {
			musics = AudioSystem.getClip();
			music = AudioSystem.getAudioInputStream(new File("src/sound/music.wav").getAbsoluteFile());
			musics.open(music);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		if(musice) {
			setMusicVol(musicVol);
			musics.start();
		}
	}
}
