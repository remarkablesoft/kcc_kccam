<template>
	<div class="input-row">
		<div class="item-label" v-text="dateTitle"></div>
		<div class="item-data flex-item range-group">
			<div v-if="$store.$common.isNotEmpty(refDateList)">
				<el-select v-model="refDate" class="size-sm" placeholder="선택" @change="emitPeriodChangedDate()">
					<el-option v-for="ref in refDateList" :key="ref.value" :label="ref.label"
							   :value="ref.value"></el-option>
				</el-select>
			</div>
			<el-date-picker v-model="searchDate" end-placeholder="종료일" format="yyyy-MM-dd" range-separator="~"
							start-placeholder="시작일" type="daterange" @change="emitPeriodDate()"
							@focus="clikCalendar()"></el-date-picker>
			<el-radio-group v-model="dateRadio" v-if="dateRadioVisible" @change="emitPeriodChangedDate()">
				<el-radio-button v-for="date in dateRadioList" :key="date.value" :label="date.value">{{ date.label }}
				</el-radio-button>
			</el-radio-group>
		</div>
	</div>
</template>
<script>
const TODAY = "TODAY";
const MONTH = "THIS_MONTH";
const YEAR = "THIS_YEAR";

export default {
	props   : {
		dateTitle   : {
			type     : String,
			required : true,
		},
        dateRadioVisible   : {
            type    : Boolean,
            required : false,
        },
		refDateList : {
			type     : Array,
			required : false,
		},
		paramName   : {
			type     : String,
			required : false,
		}
	},
	data() {
		return {
			// 기준일 - reference date
			refDate    : "",
			// 검색 기간
			searchDate : [],

			dateRadio     : "",
			dateRadioList : [
				{ label : "전체", value : "" },
				{ label : "오늘", value : TODAY },
				{ label : "당월", value : MONTH },
				{ label : "올해", value : YEAR },
			],
		};
	},
	async fetch() {
		if ( !process.client ) {
			return;
		}
		this.setRefDate();
	},
	methods : {
		// 기간으로 검색 시, 오늘/당월/금년을 일자로 형식 변경
		emitPeriodChangedDate() {
			let now;
			let modifiedDate;

			// 현재 날짜 및 시간
			this.searchDate = [];

			if ( TODAY === this.dateRadio ) {
				this.searchDate.push( new Date() );
				this.searchDate.push( new Date() );
			}
			else if ( MONTH === this.dateRadio ) {
				now = new Date();
				modifiedDate = new Date( now.setMonth( now.getMonth(), 1 ) );

				this.searchDate.push( modifiedDate );
				this.searchDate.push( new Date() );
			}
			else if ( YEAR === this.dateRadio ) {
				now = new Date(); // 현재 날짜 및 시간
				modifiedDate = new Date( now.setFullYear( now.getFullYear(), 0, 1 ) );

				this.searchDate.push( modifiedDate );
				this.searchDate.push( new Date() );
			}
			else {
				this.searchDate = null;
			}

			this.$emit( "setPeriodChangedDate", {
				searchDate : this.searchDate,
				refDate    : this.refDate,
			} );
		},

		// 직접 일자를 선택하여 검색
		emitPeriodDate() {
			this.$emit( "setPeriodDate", { searchDate : this.searchDate, refDate : this.refDate } );
		},

		// 일자를 직접 선택 시, 체크된 오늘/당월/금년 버튼 체크 해제
		clikCalendar() {
			this.dateRadio = "";
		},

		// 초기화
		resetPeriod() {
			this.setRefDate();
			this.dateRadio = "";
			this.searchDate = [];

			this.$emit( "setPeriodDate", null );
		},

		setRefDate() {
			if ( this.$common.isNotEmpty( this.refDateList ) ) {
				this.refDate = this.refDateList[ 1 ].value;
			}
		},
	},
};
</script>
