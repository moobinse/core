package com.moobin.populate;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MoobinSourceCsv implements MoobinSource {
	
	private List<List<String>> mList = new ArrayList();
	
	public MoobinSourceCsv(String pFileName) throws IOException {
		Path path = FileSystems.getDefault().getPath(pFileName);
		BufferedReader reader = Files.newBufferedReader(path);
	}
	
	public static void main(String[] args) throws IOException {
		new MoobinSourceCsv("test.csv");
	}
}
