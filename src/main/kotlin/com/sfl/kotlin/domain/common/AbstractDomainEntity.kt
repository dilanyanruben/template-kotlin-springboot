package com.sfl.kotlin.domain.common

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:23 PM
 */
@MappedSuperclass
abstract class AbstractDomainEntity {

    //region Companion object
    companion object {
        //region Constants
        private const val DEFAULT_ID = -1L
        //endregion
    }
    //endregion

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator")
    @Column(name = "id")
    var id: Long = DEFAULT_ID

    @Column(name = "created", nullable = false)
    var created: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated", nullable = false)
    var updated: LocalDateTime

    @Column(name = "removed")
    lateinit var removed: LocalDate
    //endregion

    //region Initialization
    init {
        this.updated = this.created
    }
    //endregion
}