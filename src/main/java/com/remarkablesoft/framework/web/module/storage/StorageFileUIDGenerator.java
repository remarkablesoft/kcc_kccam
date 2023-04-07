package com.remarkablesoft.framework.web.module.storage;

import com.remarkablesoft.framework.util.DateUtils;
import com.remarkablesoft.framework.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * StorageFileUID 생성
 * ex) 20140515000000019999.
 * 4 + 4 + 4  총 12자리의 파일키를 만들어낼수 있다.
 * 100,000,000,000 하루에 천억개의 파일이 생성
 * 앞에 4자리 폴더 , 다음 4자리 폴더, 그 다음 실제 파일.
 * 예시)
 * 2014
 * &nbsp;&nbsp; - 0000 폴더
 * &nbsp;&nbsp;&nbsp;&nbsp; - 0000 폴더
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - 0001 파일
 * </pre>
 *
 * 2020.01.06 james 이중화 처리
 *
 * @author james
 * @since 2014. 5. 15.
 *
 */
public class StorageFileUIDGenerator
{
	static AtomicInteger firstFolder = new AtomicInteger();
	static AtomicInteger secondFolder = new AtomicInteger();
	static AtomicInteger lastFileNumber = new AtomicInteger();

	static String systemDate = DateUtils.getCurrentDate( DateUtils.DF_YYYYMMDD );
	static int serverId = Integer.parseInt( System.getProperty( "storage.serverid", "0" ) );

	static {

		String lastStorageFile = StorageUtils.findLastStorageFile( );

		// 처음이라면..
		if ( StringUtils.isEmpty( lastStorageFile ) ) {

				// 년,월,일 다음 폴더에 폴더아이디를 저장
				firstFolder.set( serverId );

		}
		else if ( !"0".equals( lastStorageFile ) && lastStorageFile.length() == 20 ) {

	            int nFindFirstFolder = Integer.parseInt( lastStorageFile.substring( 9, 12 ) );

	            if ( nFindFirstFolder > serverId) {

	               firstFolder.set( nFindFirstFolder );
	               firstFolder.incrementAndGet();
	            }
	            else {
	               firstFolder.set( serverId );
	            }

				secondFolder.set( Integer.parseInt( lastStorageFile.substring( 12, 16 ) ) );
				lastFileNumber.set( Integer.parseInt( lastStorageFile.substring( 16, 20 ) ) );
		}

	}

	/** The Constant FILE_SEQ_GENERATOR. */
	private static final StorageFileUIDGenerator STORAGE_FILE_UID_GENERATOR = new StorageFileUIDGenerator();

	/**
	 * Instantiates a new file seq generator.
	 */
	private StorageFileUIDGenerator() {
	}

	/**
	 * Gets the file seq.
	 *
	 * @return the file seq
	 */
	public static String generateUID()
	{
		return STORAGE_FILE_UID_GENERATOR.makeUID();
	}

	/**
	 * <pre>
	 * File Seq 생성
	 * ex) 20140515000000019999.
	 * 4 + 4 + 4  총 12자리의 파일키를 만들어낼수 있다.
	 * 100,000,000,000 하루에 천억개의 파일이 생성
	 * </pre>
	 *
	 * @return the string
	 */
	public synchronized String makeUID()
	{
		String currentDate = DateUtils.getCurrentDate( DateUtils.DF_YYYYMMDD );

		folderClearCheck( currentDate);
		folderIncrement();

		String last = StringUtils.leftPad( String.valueOf( lastFileNumber.get() ), 4, "0" );
		String second = StringUtils.leftPad( String.valueOf( secondFolder.get() ), 4, "0" );
		String first = StringUtils.leftPad( String.valueOf( firstFolder.get() ), 4, "0" );

		String result = currentDate + first + second + last;

		return result;
	}

	/**
	 * 날짜가 바뀌면 폴더  초기화
	 *
	 * @author james
	 * @param currentDate
	 */
	public static void folderClearCheck( String currentDate) {

			if ( !systemDate.equals( currentDate ) ) {
				systemDate = currentDate;
				firstFolder.set( serverId );
				secondFolder.set( 0 );
				lastFileNumber.set( 0 );
			}
	}


	/**
	 * 폴더 증가처리
	 *
	 * @author james
	 */
	public static void folderIncrement() {

		lastFileNumber.incrementAndGet();

		if ( lastFileNumber.get() > 9999 ) {
			lastFileNumber.set( 0 );
			secondFolder.incrementAndGet();

			if ( secondFolder.get() > 9999 ) {
				secondFolder.set( 0 );
				//firstFolder.incrementAndGet();

				// 여기서 서버키 폴더를 찾아야 할듯.
				firstFolder.set( Integer.parseInt( StorageUtils.findFirstFolder()) );
				firstFolder.incrementAndGet();

				if ( firstFolder.get() > 9999 ) {
					new RuntimeException( "파일 한계치를 넘었습니다." );
				}
			}
		}
	}

	/**
	 * File Seq 생성 ex) 2013072614504040406.
	 *
	 * @return the string
	 */
	// public String generateFileUID() {
	//
	// String currTimeStr = FastDateFormat.getInstance( "yyyyMMddHHmmssS", Locale.getDefault()).format( new Date());
	//
	// currTimeStr = currTimeStr.substring(0, 15);
	//
	// Timestamp currentTime = new Timestamp( System.nanoTime());
	// String time = currentTime.toString().replace( ":", "").replace(".","");
	//
	// String rnumStr = time.substring( time.length() - 4, time.length());
	// return currTimeStr + String.valueOf(rnumStr);
	// }

}
