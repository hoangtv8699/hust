from scipy.io import wavfile
from matplotlib import pyplot as plt
import wave
import pylab as pl
import numpy as np

f = wave.open(r"1.wav", "rb")

# (nchannels, sampwidth, framerate, nframes, comptype, compname)
params = f.getparams()
nchannels, sampwidth, framerate, nframes = params[:4]

str_data = f.readframes(nframes)
f.close()

wave_data = np.fromstring(str_data, dtype=np.short)
wave_data.shape = -1, 2
wave_data = wave_data.T
time = np.arange(0, nframes) * (1.0 / framerate)

pl.title("sampling fequency:" + str(framerate) + "\n" + "channels: " + str(nchannels) + "\n" + "bits: " + str(sampwidth*8))
pl.subplot()
pl.plot(time, wave_data[1], c="g")
pl.xlabel("time (seconds)")
pl.show()
