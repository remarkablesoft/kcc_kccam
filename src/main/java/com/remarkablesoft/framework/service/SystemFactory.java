package com.remarkablesoft.framework.service;

import com.remarkablesoft.framework.common.ApplicationContextUtil;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.app.device.vo.DeviceRelInfo;
import com.remarkablesoft.framework.service.audit.filedown.vo.AuditFileDownInfo;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleRightInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleUserRelInfo;
import com.remarkablesoft.framework.service.board.audit.view.vo.PostingAuditViewInfo;
import com.remarkablesoft.framework.service.board.board.vo.BoardInfo;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocRelInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocVersionInfo;
import com.remarkablesoft.framework.service.link.relationship.vo.RelationshipInfo;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessInfo;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeInfo;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemDetailInfo;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemInfo;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingInfo;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupInfo;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupUserRelInfo;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendResultInfo;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateItemInfo;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;
import com.remarkablesoft.framework.service.org.group.vo.GroupInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileInfo;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	common
 * 		@프로그램 ID		:	RemarkableFactory
 * 		@프로그램 개요 		:	framework-web의 info객체의 Factory Class
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019.  9. 25.	:	james	- Info객체의 Factory Class
 * 		1.1  2019. 12. 25.	:	james 	- 이름 변경 RemarkableFactory -> SystemFactory
 * 		============================================================================
 */
public class SystemFactory  {

		private SystemFactory() {

		}

		protected static SystemFactory remarkableFactory = new SystemFactory();

		protected static SystemFactory getInstance() {

				return remarkableFactory;
		}

		// APP PACKAGE
		public static DeviceInfo getDeviceInfo() {
				return (DeviceInfo) ApplicationContextUtil.getContext().getBean( "deviceInfo" );
		}

		public static DeviceRelInfo getDeviceRelInfo() {
				return (DeviceRelInfo) ApplicationContextUtil.getContext().getBean( "deviceRelInfo" );
		}
	
		// AUDIT PACKAGE
		public static AuditViewInfo getAuditViewInfo() {
			
			return (AuditViewInfo) ApplicationContextUtil.getContext().getBean( "auditViewInfo" );
		}
		
		public static AuditVisitInfo getAuditVisitInfo() {
			return (AuditVisitInfo) ApplicationContextUtil.getContext().getBean( "auditVisitInfo" );
		}
	
		public static AuditFileDownInfo getAuditFileDownInfo() {
			return (AuditFileDownInfo ) ApplicationContextUtil.getContext().getBean( "auditFileDownInfo" );
		}

		// AUTHORITY PACKAGE
		public static RoleInfo getRoleInfo() {
				return (RoleInfo) ApplicationContextUtil.getContext().getBean( "roleInfo" );
		}
		
		public static RoleRightInfo getRoleRightInfo() {
				return (RoleRightInfo) ApplicationContextUtil.getContext().getBean( "roleRightInfo" );
		}
		
		public static RoleUserRelInfo getRoleUserRelInfo() {
				return (RoleUserRelInfo) ApplicationContextUtil.getContext().getBean( "roleUserRelInfo" );
		}

		// BOARD PACKAGE
		public static PostingAuditViewInfo getPostingAuditViewInfo() {
				return (PostingAuditViewInfo) ApplicationContextUtil.getContext().getBean( "postingAuditViewInfo" );
		}

		public static ContentsInfo getContentsInfo() {
				return (ContentsInfo) ApplicationContextUtil.getContext().getBean( "contentsInfo" );
		}

		public static PostingInfo getPostingInfo() {
				return (PostingInfo) ApplicationContextUtil.getContext().getBean( "postingInfo" );
		}

		public static BoardInfo getBoardInfo() {
				return (BoardInfo) ApplicationContextUtil.getContext().getBean( "boardInfo" );
		}
	
		// DOC PACKAGE
		public static DocInfo getDocInfo() {
			return (DocInfo) ApplicationContextUtil.getContext().getBean( "docInfo" );
		}
	
		public static DocRelInfo getDocRelInfo() {
			return (DocRelInfo) ApplicationContextUtil.getContext().getBean( "docRelInfo" );
		}
		
		public static DocVersionInfo getDocVersionInfo() {
			return (DocVersionInfo) ApplicationContextUtil.getContext().getBean( "docVersionInfo" );
		}
		
		// LINK PACKAGE
		public static RelationshipInfo getRelationshipInfo() {
			return (RelationshipInfo) ApplicationContextUtil.getContext().getBean( "relationshipInfo" );
		}
		
		// MGNT PACKAGE
		public static SystemInfo getSystemInfo() {
			return (SystemInfo) ApplicationContextUtil.getContext().getBean( "systemInfo" );
		}
	
		public static SystemDetailInfo getSystemDetailInfo() {
			return (SystemDetailInfo) ApplicationContextUtil.getContext().getBean( "systemDetailInfo" );
		}
	
		//NOTIFICATION PACKAGE
		public static MessageBookingInfo getMessageBookingInfo() {
				return (MessageBookingInfo) ApplicationContextUtil.getContext().getBean( "messageBookingInfo" );
		}

		public static MessageGroupInfo getMessageGroupInfo() {
				return (MessageGroupInfo) ApplicationContextUtil.getContext().getBean( "messageGroupInfo" );
		}

		public static MessageGroupUserRelInfo getMessageGroupUserRelInfo() {
				return (MessageGroupUserRelInfo) ApplicationContextUtil.getContext().getBean( "messageGroupUserRelInfo" );
		}

		public static MessageInfo getMessageInfo() {
				return (MessageInfo) ApplicationContextUtil.getContext().getBean( "messageInfo" );
		}

		public static MessageSendHistInfo getMessageSendHistInfo() {
				return (MessageSendHistInfo) ApplicationContextUtil.getContext().getBean( "messageSendHistInfo" );
		}

		public static MessageSendResultInfo getMessageSendResultInfo() {
				return (MessageSendResultInfo) ApplicationContextUtil.getContext().getBean( "messageSendResultInfo" );
		}

		public static MessageTemplateInfo getMessageTemplateInfo() {
				return (MessageTemplateInfo) ApplicationContextUtil.getContext().getBean( "messageTemplateInfo" );
		}

		public static MessageTemplateItemInfo getMessageTemplateItemInfo() {
				return (MessageTemplateItemInfo) ApplicationContextUtil.getContext().getBean( "messageTemplateItemInfo" );
		}

		public static BatchProcessInfo getBatchProcessInfo() {
				return (BatchProcessInfo) ApplicationContextUtil.getContext().getBean( "batchProcessInfo" );
		}

		public static CodeInfo getCodeInfo() {
				return (CodeInfo) ApplicationContextUtil.getContext().getBean( "codeInfo" );
		}
		
		public static PartInfo getPartInfo() {
				return (PartInfo) ApplicationContextUtil.getContext().getBean( "partInfo" );
		}

		public static CategoryInfo getCategoryInfo() {
				return (CategoryInfo) ApplicationContextUtil.getContext().getBean( "categoryInfo" );
		}
		
		//ORG PACKAGE
		public static BranchInfo getBranchInfo() {
				return (BranchInfo) ApplicationContextUtil.getContext().getBean( "branchInfo" );
		}

		public static UserInfo getUserInfo() {
				return (UserInfo) ApplicationContextUtil.getContext().getBean( "userInfo" );
		}
	
		public static CompanyInfo getCompanyInfo() {
			return (CompanyInfo) ApplicationContextUtil.getContext().getBean( "companyInfo" );
		}
		
		public static GroupInfo getGroupInfo() {
			return (GroupInfo) ApplicationContextUtil.getContext().getBean( "groupInfo" );
		}

		//STORAGE PACKAGE

		public static FileInfo getFileInfo() {
				return (FileInfo) ApplicationContextUtil.getContext().getBean( "fileInfo" );
		}

		public static StorageFileInfo getStorageFileInfo() {
				return (StorageFileInfo) ApplicationContextUtil.getContext().getBean( "storageFileInfo" );
		}
		
		public static ThumbnailInfo getThumbnailInfo() {
			return (ThumbnailInfo) ApplicationContextUtil.getContext().getBean( "thumbnailInfo" );
		}
		
		public static PopupInfo getPopupInfo() {
				return (PopupInfo) ApplicationContextUtil.getContext().getBean( "popupInfo" );
		}
		
}
