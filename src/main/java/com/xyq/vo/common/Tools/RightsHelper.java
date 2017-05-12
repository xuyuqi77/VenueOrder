package com.xyq.vo.common.Tools;

/**
 * @author yqxu2
 * 权限计算帮助类
 */
public class RightsHelper {

	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(String sum, int targetRights){
		if (sum.equals("admin"))
			return true;
		if (Tools.isEmpty(sum))
			return false;
		int index = sum.indexOf(String.valueOf(targetRights));
		if (index == -1)
			return false;
		return true;
	}

}
