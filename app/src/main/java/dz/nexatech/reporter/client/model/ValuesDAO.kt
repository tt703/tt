package dz.nexatech.reporter.client.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ValuesDAO {

    @Query("select * from value where value_namespace = :template")
    suspend fun loadSectionVariablesValues(template: String): List<Value>

    suspend fun loadRecordsVariablesValues(template: String): List<Value> =
        loadRecordsVariablesValuesByNamespace("$template${Record.NAMESPACE_SEPARATOR}%")

    @Query("select * from value where value_namespace like :namespace")
    suspend fun loadRecordsVariablesValuesByNamespace(namespace: String): List<Value>

    @Query("delete from value where value_namespace = :namespace and value_index = :index and value_name = :name")
    suspend fun delete(namespace: String, index: Int, name: String)

    @Query("delete from value where value_namespace = :namespace")
    suspend fun delete(namespace: String)

    suspend fun insert(namespace: String, index: Int, name: String, newContent: String) {
        insert(Value(namespace, index, name, System.currentTimeMillis(), newContent))
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newValue: Value)
}