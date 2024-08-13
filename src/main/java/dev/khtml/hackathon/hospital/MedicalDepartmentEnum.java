package dev.khtml.hackathon.hospital;

import java.util.Arrays;
import java.util.List;

public enum MedicalDepartmentEnum {
	가정의학과, 결핵과, 구강내과, 구강병리과, 구강악안면외과, 내과, 마취통증의학과,
	방사선종양학과, 병리과, 비뇨의학과, 사상체질과, 산부인과, 성형외과, 소아청소년과, 소아치과, 신경과, 신경외과,
	심장혈관흉부외과, 안과, 영상의학과, 영상치의학과, 예방의학과, 예방치과, 외과, 응급의학과, 이비인후과, 재활의학과,
	정신건강의학과, 정형외과, 직업환경의학과, 진단검사의학과, 치과, 치과교정과, 치과보존과, 치과보철과, 치주과, 침구과, 통합치의학과,
	피부과, 한방내과, 한방부인과, 한방소아과, 한방신경정신과, 한방안이비인후피부과, 한방응급, 한방재활의학과, 핵의학과;

	public static List<MedicalDepartmentEnum> getMedicalDepartments() {
		return Arrays.stream(MedicalDepartmentEnum.values()).toList();
	}
}
