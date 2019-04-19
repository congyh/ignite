/*
 * Copyright 2019 GridGain Systems, Inc. and Contributors.
 * 
 * Licensed under the GridGain Community Edition License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://www.gridgain.com/products/software/community-edition/gridgain-community-edition-license
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.cache.store.jdbc.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Person definition.
 */
public class Person implements Serializable {
    /** */
    private static final long serialVersionUID = 0L;

    /** Value for id. */
    private Integer id;

    /** Value for orgId. */
    private Integer orgId;

    /** Value for birthday. */
    private Date birthday;

    /** Value for name. */
    private String name;

    /** Value for salary. */
    private Integer salary;

    /** Value of person gender. */
    private Gender gender;

    /**
     * Empty constructor.
     */
    public Person() {
        // No-op.
    }

    /**
     * Full constructor.
     */
    public Person(
        Integer id,
        Integer orgId,
        Date birthday,
        String name,
        Integer salary,
        Gender gender
    ) {
        this.id = id;
        this.orgId = orgId;
        this.birthday = birthday;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
    }

    /**
     * Gets id.
     *
     * @return Value for id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id New value for id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets orgId.
     *
     * @return Value for orgId.
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * Sets orgId.
     *
     * @param orgId New value for orgId.
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * Gets birthday.
     *
     * @return Value for birthday.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Sets birthday.
     *
     * @param birthday New value for birthday.
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets name.
     *
     * @return Value for name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name New value for name.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets salary.
     *
     * @return Value for salary.
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     * Sets salary.
     *
     * @param salary New value for salary.
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     * Gets gender.
     *
     * @return Gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender New value for gender.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /** {@inheritDoc} */
    @Override public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Person))
            return false;

        Person that = (Person)o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;

        if (orgId != null ? !orgId.equals(that.orgId) : that.orgId != null)
            return false;

        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        if (gender != null ? !gender.equals(that.gender) : that.gender != null)
            return false;

        return true;
    }

    /** {@inheritDoc} */
    @Override public int hashCode() {
        int res = id != null ? id.hashCode() : 0;

        res = 31 * res + (orgId != null ? orgId.hashCode() : 0);

        res = 31 * res + (name != null ? name.hashCode() : 0);

        res = 31 * res + (gender != null ? gender.hashCode() : 0);

        return res;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return "Person [id=" + id +
            ", orgId=" + orgId +
            ", birthday=" + (birthday == null ? null : birthday.getTime()) +
            ", name=" + name +
            ", gender=" + gender +
            "]";
    }
}
