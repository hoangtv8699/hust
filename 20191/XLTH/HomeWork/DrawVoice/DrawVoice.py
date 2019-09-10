import matplotlib.pyplot as plt
from scipy.io import wavfile as wav
rate, data = wav.read('1.wav')
plt.title("sampling frequency: " + str(rate))
plt.plot(data)
plt.show()

