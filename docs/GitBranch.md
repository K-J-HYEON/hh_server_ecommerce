## Git Branch 전략 초안
- feature -> dev -> main(prod)


### feature
1. 기능을 개발하는 브랜치, 즉 새로운 기능 개발 및 버그 수정이 필요할 때마다 develop 브랜치로부터 분기함
2. feature 브랜치 작업은 로컬 저장소에서 관리하고 개발이 완료되면 develop 브랜치로 병합(merge)하여 다른사람들과 공유



### dev
1. 기능 개발을 위한 브랜치들을 병합하기 위해 사용
2. 기능이 추가되고 버그가 수정되어 배포 가능한 상태이면 main branch에 병합(merge)
3. dev 브랜치를 기반으로 개발 진행



### main
1. 제품으로 출시될 수 있는 최종 브랜치
2. 배포 이력을 관리하기 위해 사용, 배포 가능한 상태만을 관리



##



## 현업에서 전체적인 github workflow
#### Main -> Hotfix -> Release -> Develop -> Feature1 -> Feature2 -> etc...

##

### 브랜치 생성규칙
1. main -> 하나의 main 브랜치만 사용, 배포 시 Tag 및 업데이터 내용 추가 작성, 추가 생성하지 않음.
2. develop -> 하나의 develop 브랜치만 사용, 추가 생성하지 않음
3. release -> 배포 버전 명으로 생성, 해당 버전 배포 전까지만 사용
4. feature -> 해당 feature 개발자가 최신 버전의 develop에서 feature/<기능명>으로 생성
5. hotfix -> hotfix<내용>으로 생성

##

### Git 조작 순서 및 방법
#### 새로운 feature 개발: 최근 develop의 변경사항을 local에 반영시킨 후, 새로운 브랜치를 생성하여 작업
2. git checkout develop: devleop 브랜치로 checkout
3. git pull: develop 브랜치의 최근 변경사항을 로컬로 가져옴
4. git checkout -b <feature branch명>: 명령어를 통해 브랜치 생성 후 checkout한다.(alias: checkout -> co)
5. 코드작성
6. git add <file 명>: git add . 을 통해 모든 파일을 staging area에 추가할 수 있음.
7. git commit -m "커밋메세지"(alias: commit -> cm)
8. git push origin <feature branch>: origin(원격 저장소)의 feature branch로 로컬 변경 내역 push
9. github에서 feature branch -> develop branch(방향으로) Pull Request 진행
10. 리뷰가 종료되고 모두 Approve 된다면 Merge 진행.


##


#### 배포 후 hotfix가 필요할 때
1. git checkout main: 현재 배포 중인 main 브랜치로 이동
2. git checkout -b <hotfix branch>: 핫픽스 브랜치 생성
3. hotfix, 수정 코드 작성
4. git add <file 명>: git add .을 통해 모든 파일을 staging area에 추가할 수 있음
5. git commit -m "커밋메세지" (alias: commit -> cm)
6. git push origin <hotfix branch>
7. github에서 hotfix branch -> develop branch 방향으로 Pull Request
8. github에서 hotfix branch -> main branch 방향으로 Request
9. 각각 main과 develop으로의 PR 리뷰가 완료되면 Merge


##


#### release 준비
1. 원격 저장소의 develop 브랜치에 이번 버전에 업데이트할 모든 feature가 머지되었는지 확인
2. git checkout develop: 로컬 develop 브랜치로 이동
3. git pull: 원격 저장소의 변경사항을 로컬로 가져옴
4. git checkout -b <release branch>: release 브랜치 생성
5. release 브랜치에서 빌드 후에 버그 유무 확인
6. 버그 확인 시 bugfix 후 add, commit, push -> develop 브랜치에 PR
7. git push origin <release branch>
8. github에서 release branch -> develop branch 방향으로 Pull Request
9. github에서 release branch -> main branch 방향으로 Pull Request
10. 리뷰가 완료되면 배포사항 Merge
