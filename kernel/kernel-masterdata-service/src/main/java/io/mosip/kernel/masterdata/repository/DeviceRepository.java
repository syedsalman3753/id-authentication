package io.mosip.kernel.masterdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.mosip.kernel.core.dataaccess.spi.repository.BaseRepository;
import io.mosip.kernel.masterdata.entity.Device;

/**
 * Repository function to fetching device details
 * 
 * @author Megha Tanga
 * @since 1.0.0
 *
 */

@Repository
public interface DeviceRepository extends BaseRepository<Device, String> {

	/**
	 * This method trigger query to fetch the Device detail for the given id.
	 * 
	 * @param id the id of device
	 * @return the device detail
	 */
	@Query("FROM Device d where d.id = ?1 AND (d.isDeleted is null or d.isDeleted = false) AND d.isActive = true")
	List<Device> findByIdAndIsDeletedFalseOrIsDeletedIsNull(String id);

	/**
	 * This method trigger query to fetch the Device detail for the given language
	 * code.
	 * 
	 * 
	 * @param langCode language code provided by user
	 * 
	 * @return List Device Details fetched from database
	 */

	@Query("FROM Device d where d.langCode = ?1 and (d.isDeleted is null or d.isDeleted = false) AND d.isActive = true")
	List<Device> findByLangCodeAndIsDeletedFalseOrIsDeletedIsNull(String langCode);

	/**
	 * This method trigger query to fetch the Device detail for the given language
	 * code and Device Type code.
	 * 
	 * 
	 * @param langCode       language code provided by user
	 * @param deviceTypeCode device Type Code provided by user
	 * @return List Device Details fetched from database
	 * 
	 */
	@Query(value = "select d.id, d.name, d.mac_address, d.serial_num, d.ip_address, d.dspec_id, d.lang_code, d.is_active, d.validity_end_dtimes, s.dtyp_code from master.device_master  d, master.device_spec s where  d.dspec_id = s.id  and d.lang_code = s.lang_code and d.lang_code = ?1 and s.dtyp_code = ?2 and (d.is_deleted is null or d.is_deleted = false) and d.is_active = true", nativeQuery = true)
	List<Object[]> findByLangCodeAndDtypeCode(String langCode, String deviceTypeCode);

	/**
	 * This method trigger query to fetch the Machine detail for the given id code.
	 * 
	 * @param deviceSpecId machineSpecId provided by user
	 * 
	 * @return MachineDetail fetched from database
	 */

	@Query("FROM Device d where d.deviceSpecId = ?1 and (d.isDeleted is null or d.isDeleted = false) and d.isActive = true")
	List<Device> findDeviceByDeviceSpecIdAndIsDeletedFalseorIsDeletedIsNull(String deviceSpecId);

	/**
	 * This method trigger query to fetch the Device detail for the given id and
	 * language code.
	 * 
	 * @param id       the id of device
	 * @param langCode language code from user
	 * @return the device detail
	 */
	@Query("FROM Device d where d.id = ?1 and d.langCode = ?2 AND (d.isDeleted is null or d.isDeleted = false) and d.isActive = true")
	Device findByIdAndLangCodeAndIsDeletedFalseOrIsDeletedIsNull(String id, String langCode);
	
	/**
	 * This method trigger query to fetch the Device detail those are mapped with
	 * the given regCenterId
	 * 
	 * @param regCenterId
	 *            regCenterId provided by user
	 * @return Device 
	 *           fetched all device those are mapped with the given registration center from database
	 */
	@Query(value = "SELECT dm.id, dm.name, dm.mac_address, dm.serial_num, dm.ip_address, dm.dspec_id, dm.lang_code, dm.is_active, dm.validity_end_dtimes, dm.cr_by, dm.cr_dtimes, dm.upd_by, dm.upd_dtimes, dm.is_deleted, dm.del_dtimes FROM master.device_master dm inner join master.reg_center_device rcd on dm.id = rcd.device_id where (rcd.is_deleted is null or rcd.is_deleted=false)  and (dm.is_deleted is null or dm.is_deleted=false) and rcd.regcntr_id =?1", nativeQuery = true)
	List<Device> findDeviceByRegCenterId(String regCenterId);

}
