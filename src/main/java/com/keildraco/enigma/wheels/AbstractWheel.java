package com.keildraco.enigma.wheels;

import com.keildraco.enigma.interfaces.IEnigmaWheel;

public abstract class AbstractWheel implements IEnigmaWheel {
	private final char[] alphabet;
	private boolean step;
	private final char[] stepOn;
	private int rotIdx;
	
	public AbstractWheel(String inputAlphabet, String stepChars, char initPos) {
		this.alphabet = inputAlphabet.toCharArray();
		this.step = false;
		this.stepOn = stepChars.toCharArray();
		this.rotIdx = initPos - 'A';
	}

	@Override
	public char encodeChar(char input) {
		int idx = input - 'A';
		char rv = this.alphabet[maybeRotate(idx)];
		for(char p : this.stepOn) {
			if(rotIdx == p) this.step = true;
		}
		return rv;
	}

	public void rotate() {
		this.rotIdx++;
		this.rotIdx %= 26;
	}
	
	protected int maybeRotate(int idx) {
		if(this.step) {
			rotate();
			this.step = false;
		}
		return rotIdx+idx;
	}
}
