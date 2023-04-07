<template>
	<div>
		<!--				<div class="loading-container" v-if="showLoadingIndicator">-->
		<!--					<the-loading />-->
		<!--				</div>-->
		<div v-if="showLoadingIndicator"
		     class="no-data">
			<div class="loading-sm">
				<img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
			</div>
			<p>데이터 로딩중입니다.</p>
		</div>
		<!-- <p class="save-txt" v-show="isEdit" v-text="saveText"></p> -->
		<!--노드(카테고리) 주석 설명-->
		<!-- <div class="top-area" v-show="isEdit">
            <div class="open-group" :class="[toggleArea ? addClass : '']">
                <div class="reduce-area">
                    <button class="material-icons"
                    @click="toggleArea ? toggleArea = false : toggleArea = true">close</button>
                </div>

                <div class="tit">
                    <i class="material-icons edit-icon">info_outline</i>
                    <span>도움말</span>
                </div>
                <div class="item-group">
                    <div class="item">
                        <i class="material-icons edit-icon">create</i>
                        <span>카테고리 수정</span>
                    </div>

                    <div class="item">
                        <i class="material-icons">delete_forever</i>
                        <span>카테고리 삭제</span>
                    </div>
                </div>
                <div class="item-group">
                    <div class="item">
                        <img src="~/assets/ebook/images/icon/icon_categoryLow_gray.png" class="low-categori" alt="하위 카테고리 추가" />
                        <span>하위 카테고리 추가</span>
                    </div>

                    <div class="item">
                        <img src="~/assets/ebook/images/icon/icon_categoryMid_gray.png" class="mid-categori" alt="하위 카테고리 추가" />
                        <span>동일 카테고리 추가</span>
                    </div>
                </div>
            </div>

            <div class="close-group" :class="[toggleArea ? addClass : '']">
                <div class="tit" @click="toggleArea ? toggleArea = false : toggleArea = true">
                    <i class="material-icons edit-icon">info_outline</i>
                    <span>도움말</span>
                </div>
            </div>

        </div> -->
		<tree
			:data="treeData"
			:options="treeOptions"
			ref="tree"
			@node:dragging:start="logDragStart"
			@node:dragging:finish="logDragFinish"
			@node:selected="click"
			@tree:mounted="sendRoot"
		>
			<div slot-scope="{ node }" class="node-container">
				<!--전체-->
				<!--이부분 왼쪽클릭하면 메뉴가생성되도록-->
				<div class="node-text">{{ node.text }}
				                       <!-- <i class="material-icons set" v-show="$constant.FLAG_YN.NO === node.data.useYn || $constant.FLAG_YN.NO === node.data.statusTypeFlag">visibility_off</i> -->
				</div>
				<!-- <i class="custom-icons set" v-show="$constant.FLAG_YN.NO === node.data.useYn || $constant.FLAG_YN.NO === node.data.statusTypeFlag">
					<div class="icon-group">
						<div class="icon icon-line-h"></div>
						<div class="icon icon-line-v"></div>
					</div>

				</i>
			</div> -->
				<div class="node-controls" v-show="isEdit">
					<!-- dropdown menu -->
					<el-dropdown class="tree-dropdown"
					><!-- trigger="click" -->
						<span class="el-dropdown-link" @click.stop="">
							<i class="material-icons set">settings</i>
						</span>
						<el-dropdown-menu slot="dropdown" class="tree-dropdown-menu">
							<el-dropdown-item>
								<!--수정-->
								<el-button
									size="mini"
									@click.stop="editNode(node)"
									round
									title="카테고리 수정"
									v-if="node.text != '전체'"
								>
									<i class="material-icons edit-icon">create</i>
									<span class="txt">수정</span>
								</el-button>
							</el-dropdown-item>
							<el-dropdown-item>
								<!--삭제-->
								<el-button
									class="el-delete"
									size="mini"
									@click.stop="removeNode(node)"
									round
									title="카테고리 삭제"
									v-if="node.text != '전체'"
								>
									<i class="material-icons edit-icon">delete_forever</i>
									<span class="txt">삭제</span>
								</el-button>
							</el-dropdown-item>
							<el-dropdown-item>
								<!--하위 노드 추가-->
								<el-button
									size="mini"
									@click.stop="addChildNode(node)"
									round
									v-show="node.data.depth < 3"
									class="low-categori-add"
									title="하위 카테고리 추가"
								>
									<!--변경전 하위노드-->
									<i class="material-icons">add_circle</i>
									<span class="txt">추가</span>
								</el-button>
							</el-dropdown-item>
						</el-dropdown-menu>
					</el-dropdown>
					<!--수정-->
					<!-- <el-button size="mini" @click.stop="editNode(node)" round title="카테고리 수정">
						<i class="material-icons edit-icon">create</i>
					</el-button> -->
					<!--삭제-->
					<!-- <el-button
						class="el-delete"
						size="mini"
						@click.stop="removeNode(node)"
						round
						title="카테고리 삭제"
						v-show="node.text != '전체'"
					>
						<i class="material-icons edit-icon">delete_forever</i>
					</el-button> -->
					<!--하위 노드 추가-->
					<!-- <el-button
						size="mini is-icon"
						@click.stop="addChildNode(node)"
						round

						class="low-categori-add"
						title="하위 카테고리 추가"
					>
						<span class="icon-item"> -->
					<!--변경전 하위노드-->
					<!-- <i class="material-icons sm">add_circle</i>
							<span class="txt">추가</span>
						</span>
					</el-button> -->
					<!--노드 추가-->
					<!-- <el-button
						size="mini is-icon"
						class="mid-categori-add"
						@click.stop="addNode(node)"
						round
						title="카테고리 추가"
						v-show="node.text != '전체'"
					>
						<span class="icon-item"> -->
					<!--변경전 동일노드-->
					<!-- <i class="material-icons sm">add_circle</i>
							<span class="txt">카테고리 추가</span>
						</span>
					</el-button> -->
				</div>
			</div>
		</tree>
	</div>
</template>
<script>
import Vue from "vue";
import LiquorTree from "liquor-tree";
import theLoading from "~/components/common/loading/TheLoading.vue";

Vue.use( LiquorTree );

export default {
	components : {
		theLoading,
	},

	props : {
		type : {
			required : true,
			type     : String,
		},

		editType : {
			required : false,
			type     : String,
			default  : "view"
		},

		setOidList : {
			required : false,
			type     : Array,
			default  : () => {
				return [];
			},
		},

		multiple : {
			required : false,
			type     : Boolean,
			default  : false,
		},

		partOid : {
			type : String,
		}
	},


	data() {
		return {
			treeOptions : {
				propertyNames : {
					id       : "oid",
					text     : "name",
					children : "childTreeNodeList",
					data     : "data",
				},
				dnd           : false,
				multiple      : true,
				checkBox      : false,
				parentSelect  : false,
			},

			showLoadingIndicator : false,
			treeData             : null,

			isEdit   : false,
			saveText : "자동으로 저장됩니다.",

			showTree : true,

			activeType : "",

			// API 호출 url
			list_url   : "",
			save_url   : "",
			delete_url : "",
			//도움말 토글 관련
			// toggleArea: true,
			// addClass:'active',

			rootData : {},
		};
	},

	created() {
		if ( !process.client ) {
			return;
		}
		if ( "edit" == this.editType ) {
			this.isEdit = true;
			//this.treeOptions.dnd = true;
		}
		else if ( "select" == this.editType ) {
			this.treeOptions.multiple = false;
			this.treeOptions.parentSelect = true;
		}
		this.showLoadingIndicator = true;

		/**
		 * props 값에 따라 API를 호출할 URL 세팅
		 */

		// if ( -1 != this.type.indexOf("Menu") ) {
		// 	this.activeType = "메뉴"
		// 	//param.className = this.type;
		// }

		// if ( "trainingCourse" === this.type ) {
		// 	this.activeType = "연수과정분류"
		// }

		// if ( "contentCourse" === this.type ) {
		// 	this.activeType = "학습과정분류"
		// }

		// this.setUrl();
		this.getNodeData();
	},

	watch : {
		partOid() {
			this.getNodeData();
		}
	},

	mounted() {
		this.$refs.tree.$on( "node:editing:start", ( node ) => {
			// 			console.log("Start editing: " + node.text);
		} );

		this.$refs.tree.$on( "node:editing:stop", ( node, isTextChanged ) => {
			// 			console.log("Stop editing: " + node.text + ", " + isTextChanged);

			let param = {};
			param.oid = node.id;
			param.name = node.text;

			this.saveNode( param );
		} );
	},

	methods : {

		// setUrl() {
		// 	if ( "메뉴" === this.activeType ) {
		// 		this.list_url = URL_LIST_MENU;
		// 		this.save_url = URL_SAVE_MENU;
		// 		this.delete_url = URL_DELETE_MENU;
		// 	}

		// 	if ( "연수과정분류" === this.activeType ) {
		// 		this.list_url = URL_LIST_TRAINING_COURSE;
		// 		this.save_url = URL_SAVE_TRAINING_COURSE
		// 		this.delete_url = URL_DELETE_TRAINING_COURSE;
		// 	}

		// 	if ( "학습과정분류" === this.activeType ) {
		// 		this.list_url = URL_LIST_CONTENT_COURSE;
		// 		this.save_url = URL_SAVE_TCONTENT_COURSE
		// 		this.delete_url = URL_DELETE_CONTENT_COURSE;
		// 	}

		// },

		getNodeData() {
			console.log( "getNodeData" );
			this.showLoadingIndicator = true;

			let param = {
				partOid      : this.partOid, //material oid
				categoryType : this.type,
			};

			let url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST_ALL_TREE_CHILDREN;

			this.treeData = new Promise( ( resolve ) => {
				this.$axios
					.post( url, param )
					.then( ( res ) => {
						this.showLoadingIndicator = false;
						this.showTree = true;
						console.log( "NodeList", res.data )
						if ( 0 === res.data.length ) {
							return;
						}

						if ( "ROOT" == res.data[ 0 ].name ) {
							res.data[ 0 ].name = "전체";
						}

						let list = res.data;

						// if ( "연수과정분류" === this.activeType || "학습과정분류" === this.activeType ) {
						// 	list = res.data[0].childTreeNodeList;
						// }

						list = this.fillNode( list, false );

						console.log( "treedata", list );
						resolve( list );
					} );
			} );

			this.$forceUpdate();
		},

		fillNode( nodeList, isChild, parentNode ) {
			if ( this.$common.isEmpty( nodeList ) || nodeList.length == 0 ) {
				return;
			}
			if ( !isChild ) {
				nodeList.forEach( ( node, index ) => {
					node.data = {
						parentOid    : node.parentOid,
						depth        : node.depthVC,
						fullPathName : node.name,
					};

					// if ( "메뉴" === this.activeType ) {
					// 	node.data.menuTypeFlag = node.menuTypeFlag;
					// 	node.data.boardOid = node.boardOid;
					// 	node.data.url = node.url;
					// 	node.data.favorite = node.favorite;
					// 	node.data.rightInfoList = node.rightInfoList;
					// 	node.data.targetOid = node.targetOid;
					// 	node.data.targetObject = node.targetObject;
					// 	node.data.useYn = node.useYn;
					// 	node.data.boardInfo = node.boardInfo;
					// };

					// if ( "연수과정분류" === this.activeType ) {
					// 	node.data.useYn = node.useYn;
					// };

					// if ( "학습과정분류" === this.activeType ) {
					// 	node.data.statusTypeFlag = node.statusTypeFlag;
					// };

					node.state = {
						expanded : true,
					};

					if ( this.setOidList[ 0 ] == node.oid ) {
						node.state = {
							selected : true,
						};
					}

					let childTreeNodeList = node.childCategoryList;

					if ( this.$common.isNotEmpty( childTreeNodeList ) ) {
						node.childTreeNodeList = this.fillNode(
							childTreeNodeList,
							true,
							node,
						);
					}
				} );

				return nodeList;
			}
			if ( isChild ) {
				nodeList.forEach( ( childNode, index ) => {
					childNode.data = {
						parentOid    : childNode.parentOid,
						depth        : childNode.depthVC,
						fullPathName : parentNode.data.fullPathName + " > " + childNode.name,
					};

					// if ( "메뉴" === this.activeType ) {
					// 	childNode.data.menuTypeFlag = childNode.menuTypeFlag;
					// 	childNode.data.boardOid = childNode.boardOid;
					// 	childNode.data.url = childNode.url;
					// 	childNode.data.favorite = childNode.favorite;
					// 	childNode.data.rightInfoList = childNode.rightInfoList;
					// 	childNode.data.targetOid = childNode.targetOid;
					// 	childNode.data.targetObject = childNode.targetObject;
					// 	childNode.data.useYn = childNode.useYn;
					// 	childNode.data.boardInfo = childNode.boardInfo;
					// };

					// if ( "연수과정분류" === this.activeType ) {
					// 	childNode.data.useYn = childNode.useYn;
					// };

					// if ( "학습과정분류" === this.activeType ) {
					// 	childNode.data.statusTypeFlag = childNode.statusTypeFlag;
					// };

					if ( this.setOidList[ 0 ] == childNode.oid ) {
						( childNode.state = {
							selected : true,
						} ),
							( parentNode.state = {
								expanded : true,
							} );
					}

					if ( childNode.selected ) {
						parentNode.state = {
							expanded : true,
						};
					}

					let childTreeNodeList = childNode.childCategoryList;

					if ( this.$common.isNotEmpty( childTreeNodeList ) ) {
						childNode.childTreeNodeList = this.fillNode(
							childTreeNodeList,
							true,
							childNode,
						);
					}
				} );
				return nodeList;
			}
		},

		sendRoot() {
			if ( "select" !== this.editType ) {
				console.log( "tree", this.$refs.tree.model )
				this.$emit( "sendNodeData", this.$refs.tree.model[ 0 ] );
			}
		},

		getSelectedData() {
			if ( "select" === this.editType ) {
				let selection = this.$refs.tree.selected()
				console.log( "selection", selection[ 0 ] )
				return selection[ 0 ];
			}
		},

		// select 해제
		unselectNode() {
			let selection = this.$refs.tree.selected()
			selection.unselect();
		},

		//노드 클릭시
		click( node ) {
			if ( "select" !== this.editType ) {
				// 한번에 접히지 않고 두번 클릭하면 접힘
				node.toggleExpand();

				this.$emit( "sendNodeData", node );

			}
		},

		//노드 수정시
		// editNode(node, e) {
		// 	node.startEditing();
		// },

		async editNode( node ) {
			let param = {};
			param = node.data;
			param.oid = node.id;
			param.name = node.data.text;

			await this.saveNode( param );
		},

		//노드 삭제시
		removeNode( node ) {
			swal( {
				title : "해당 " + this.activeType + "를 삭제하시겠습니까?",
				//icon: "warning",
				text       : "하위 정보도 모두 삭제됩니다.",
				dangerMode : true,
				buttons    : {
					confirm : "삭제",
					cancel  : "취소",
				},
			} ).then( ( willDelete ) => {
				if ( willDelete ) {
					node.remove();

					let param = {};
					param.oid = node.id;

					this.deleteNode( param );

					return true;
				}
			} );
		},

		//하위노드 추가시
		async addChildNode( node ) {
			if ( node.enabled() ) {
				let nodeText = "새 " + this.activeType;

				let param = {};
				param.parentOid = node.id;
				param.name = nodeText;
				param.inputUser = this.$store.state.login.loginUser.userOid;

				// if ( "메뉴" === this.activeType ) {
				// 	param.className = this.type;
				// };

				// if ( "연수과정분류" === this.activeType || "학습과정분류" === this.activeType ) {
				// 	param.partOid = this.$constant.EDUCATION_TYPE.ONTACT.PART_OID;
				// };

				let oid = await this.saveNode( param );

				let newNode = {
					id   : oid,
					text : nodeText,
					data : {
						depth        : node.data.depth + 1,
						fullPathName : node.data.fullPathName + " > " + nodeText,
					},
				};

				// if ( "메뉴" === this.activeType ) {
				// 	newNode.data.menuTypeFlag = this.$constant.MENU_TYPE_FLAG.EMPTY.KEY;
				// 	newNode.data.useYn = this.$constant.FLAG_YN.NO;
				// };


				node.append( newNode );
				node.expand();

				//==== hoon  style 추가 ====
				// nodeTreeChildSelect
				// var domTreeObj = {
				//     // 자식노드가 생성될때 나오는 ul 그룹입니다
				//     treeChild: document.querySelector('.tree-children'),
				//     // 트리 노드입니다
				//     treeNodes: document.querySelector('.tree-node'),
				// };

				// console.log("잡은 돔",domTreeObj.treeChild);


			}
		},

		//동일노드 추가시
		async addNode( node ) {
			if ( node.enabled() ) {
				let nodeText = "새 " + this.activeType;
				//console.log("addNode", node)

				let param = {};
				param.name = nodeText;
				param.parentOid = node.parent.id;
				param.inputUser = this.$store.state.login.loginUser.userOid;

				// if ( "메뉴" === this.activeType) {
				// 	param.className = this.type;
				// };

				// if ( "연수과정분류" === this.activeType || "학습과정분류" === this.activeType ) {
				// 	param.partOid = this.$constant.EDUCATION_TYPE.ONTACT.PART_OID;
				// };

				let oid = await this.saveNode( param );

				if ( node.parent != null ) {
					if ( node.parent.children.length > 0 ) {
						let finalNode = node.parent.children[ node.parent.children.length - 1 ];

						let newNode = {
							id   : oid,
							text : nodeText,
							data : {
								depth        : node.data.depth,
								fullPathName : node.parent.data.fullPathName + " > " + nodeText,
							},
						};

						// if ( "메뉴" === this.activeType) {
						// 	newNode.data.menuTypeFlag = this.$constant.MENU_TYPE_FLAG.EMPTY.KEY;
						// 	newNode.data.useYn = this.$constant.FLAG_YN.NO;
						// };

						// if ( "연수과정분류" === this.activeType ) {
						// 	newNode.data.useYn = this.$constant.FLAG_YN.NO;
						// };

						// if ( "학습과정분류" === this.activeType ) {
						// 	newNode.data.statueTypeFlag = this.$constant.FLAG_YN.NO;
						// };

						finalNode.after( newNode );

						console.log( "addNode", newNode.id );
					}
				}
				else {
					let finalNode = node.tree.model[ node.tree.model.length - 1 ];

					let newNode = {
						id   : oid,
						text : nodeText,
						data : {
							depth        : node.data.depth,
							fullPathName : node.parent.data.fullPathName + " > " + nodeText,
						},
					}

					// if ( "메뉴" === this.activeType) {
					// 	newNode.data.menuTypeFlag = this.$constant.MENU_TYPE_FLAG.EMPTY.KEY;
					// 	newNode.data.useYn = this.$constant.FLAG_YN.NO;
					// }

					// if ( "연수과정분류" === this.activeType ) {
					// 	newNode.data.useYn = this.$constant.FLAG_YN.NO;
					// };

					// if ( "학습과정분류" === this.activeType ) {
					// 	newNode.data.statueTypeFlag = this.$constant.FLAG_YN.NO;
					// };

					finalNode.after( newNode );

					console.log( "addNode", newNode.id );
				}
			}
		},

		initEventViewer( event ) {
			const events = this.events;
			return function ( node, newNode ) {
				let nodeText = "-";
				let targetNode = newNode && newNode.text ? newNode : node;

				if ( targetNode && targetNode.text ) {
					nodeText = targetNode.text;
				}

				events.push( Object.assign( {}, event, { time : new Date(), nodeText, id : key++ } ) );

				// 				console.log(event, arguments);
			};
		},

		logDragStart( node ) {
			// 			console.log("Start dragging: " + node.text);
		},

		logDragFinish( targetNode, distinationNode ) {
			let moveType = "";

			let targetDepth = targetNode.depth;
			let distinationDepth = distinationNode.depth;

			if ( targetDepth == distinationDepth ) {
				let targetNodeIndex = 0;
				let distinationIndex = 0;

				targetNode.tree.model.forEach( ( item, index ) => {
					if ( targetNode.id == item.id ) {
						targetNodeIndex = index;
					}

					if ( distinationNode.id == item.id ) {
						distinationIndex = index;
					}
				} );

				if ( targetNodeIndex > distinationIndex ) {
					moveType = "up";
				}
				else {
					moveType = "down";
				}
			}
			else {
				moveType = "child";
			}

			targetNode.remove();

			let param = {};
			param.nodeOid = targetNode.id;
			param.targetOid = distinationNode.id;
			param.direction = moveType;

			this.moveNode( param );
		},

		moveNode( param ) {
			this.$axios.post( this.save_url, param ).then( ( res ) => {
			} );
		},

		async saveNode( param ) {
			this.saveText = "저장중...";
			let oid = "";

			console.log( "param", param );

			await this.$axios.post( this.save_url, param ).then( ( res ) => {
				this.saveText = "저장되었습니다.";
				// this.showTree = false;
				oid = res.data.oid;
				this.getNodeData();
			} );
			console.log( "saveNode", oid );

			return oid;

		},

		deleteNode( param ) {
			this.$axios.post( this.delete_url, param ).then( ( res ) => {
				this.getNodeData();
			} );
		},
	},
};
</script>
