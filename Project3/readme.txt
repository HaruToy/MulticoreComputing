vscode를 사용했다.

-----------Prob1-------------
1. 빌드

tasks.json을 다음과 같이 설정해 빌드 시 -fopenmp를 추가되도록 했다. 
{
	"version": "2.0.0",
	"tasks": [
		{
			"type": "cppbuild",
			"label": "C/C++: g++.exe 활성 파일 빌드",
			"command": "C:\\mingw64\\bin\\g++.exe",
			"args": [
				"-g",
				"-fopenmp",
				"${file}",
				"-o",
				"${fileDirname}\\${fileBasenameNoExtension}.exe"
			],
			"options": {
				"cwd": "${fileDirname}"
			},
			"problemMatcher": [
				"$gcc"
			],
			"group": "build",
			"detail": "컴파일러: C:\\mingw64\\bin\\g++.exe"
		},
        {
            "label": "execute",
            "command": "cmd",
            "group": "test",
            "args": [
                "/C",
                "${fileDirname}\\${fileBasenameNoExtension}"
            ]
        }
	]
}

2. 실행
터미널에 "prob1.exe(빌드 후 생성된) [type number] [thread number]"를 입력한다. 

------------prob2--------------
main 메소드 상단에 뜨는 "Run"을 누르면 바로 실행된다.
