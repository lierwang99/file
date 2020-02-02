package myStudy.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class GeneralFile {

	public static String readOut(String filePath,String charsetName, String delimiter, int row, int column) {
		filePath = filePath.trim();
		File file = new File(filePath); // reciprocal adj 倒数的，互惠的，相应的
		if (!file.exists() || !file.isFile() || filePath.length() < 5) {
			return null;
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),charsetName.trim()));
			String line = "";
			List<String> list = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				list.add(line);// 这一行不为空就加入list集合
			}
			String arr[] = new String[list.size()];
			list.toArray(arr);
			row--;
			column--;
			if (row < arr.length && row >= 0) {
				String single[] = arr[row].split(delimiter);
				if (column < single.length && column >= 0) {
					// single[column].length()!=0 这样判断也可以，0长度的String也不等于null
						return single[column];
				} else {
					System.out.println("第 " + (row + 1) + " 行数据最大列数为：" + single.length);
					return null;
				}
			} else {
				System.out.println("文件最大行数为：" + list.size());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("超越数据区域边界");
		return null;
	}
	public static String[] readLines(String filePath,String charsetName) {
		File file = new File(filePath.trim());
		String str = null;
		List<String> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),charsetName.trim()));
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String[] arr = new String[list.size()];
		arr = list.toArray(arr);
		return arr;
	}
	

	public static boolean writeIn(String filePath, String charsetName,String content) {
		boolean flag = false;
		BufferedWriter bw = null;
		filePath = filePath.trim();
		File file = new File(filePath); // reciprocal adj 倒数的，互惠的，相应的
		if (!file.exists() || !file.isFile() || filePath.length() < 5) {
			return flag;
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filePath,true);
			bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream,charsetName));
			if (file.length() == 0) {
				bw.append(content);
			} else {
				bw.newLine();
				bw.append(content);
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
